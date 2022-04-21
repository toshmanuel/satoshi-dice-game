package com.qala.dimeji.satoshidice.utils;


import com.qala.dimeji.satoshidice.service.ConfigFile;
import org.bitcoinj.core.*;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MySQLFullPrunedBlockStore;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

@Configuration
public class SatoshiBeans {

    @Autowired
    private ConfigFile configFile;

    @Bean
    public BlockChain blockChain() throws BlockStoreException, UnreadableWalletException {
        BlockChain chain = new BlockChain(RegTestParams.get(), new MySQLFullPrunedBlockStore(
                RegTestParams.get(),
                10,
                configFile.getLedgerHost(),
                configFile.getLedgerName(),
                configFile.getLedgerUser(),
                configFile.getLedgerPassword())
        );
        chain.addWallet(wallet());

        return chain;
    }

    @Bean
    public Peer peer() throws BlockStoreException, UnknownHostException, ExecutionException, InterruptedException, UnreadableWalletException {
        PeerGroup peerGroup = new PeerGroup(RegTestParams.get(), blockChain());
        peerGroup.addAddress(new PeerAddress(RegTestParams.get(), InetAddress.getLocalHost()));

        peerGroup.addWallet(wallet());
        DownloadProgressTracker blockList = new DownloadProgressTracker() {
            @Override
            public void doneDownload() {
                System.out.println("Blockchain download completed!!!");
            }
        };
        peerGroup.start();
        peerGroup.connectToLocalHost();
        peerGroup.startBlockChainDownload(blockList);
        peerGroup.waitForPeers(1).get();
        return peerGroup.getConnectedPeers().get(0);
    }

    @Bean
    public Wallet wallet() throws UnreadableWalletException {
        DeterministicSeed seed = new DeterministicSeed(configFile.getMnemonicCode(), null, "", configFile.getCreationTime());
        return Wallet.fromSeed(RegTestParams.get(), seed, Script.ScriptType.P2WPKH);
    }

}
