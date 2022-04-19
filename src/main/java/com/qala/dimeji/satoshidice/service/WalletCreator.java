package com.qala.dimeji.satoshidice.service;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MySQLFullPrunedBlockStore;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
@Slf4j
public class WalletCreator {

    @Autowired
    private ConfigFile configFile;

    public Wallet createWallet(NetworkParameters parameters, Script.ScriptType scriptType) throws UnreadableWalletException, BlockStoreException {
        DeterministicSeed seed = new DeterministicSeed(configFile.getMnemonicCode(), null, "", configFile.getCreationTime());
        Wallet wallet = Wallet.fromSeed(parameters, seed, scriptType);
        BlockChain chain = new BlockChain(parameters, wallet, new MySQLFullPrunedBlockStore(
                parameters,
                10,
                configFile.getLedgerHost(),
                configFile.getLedgerName(),
                configFile.getLedgerUser(),
                configFile.getLedgerPassword())
        );
        log.info("chain is ->{}", chain.getClass());
        PeerGroup peerGroup = new PeerGroup(parameters, chain);
//        peerGroup.connectToLocalHost();
        peerGroup.addWallet(wallet);
        peerGroup.start();
        peerGroup.connectTo(new InetSocketAddress("localhost", 3002));
        peerGroup.startBlockChainDownload(new DownloadProgressTracker());

        return wallet;
    }

}
