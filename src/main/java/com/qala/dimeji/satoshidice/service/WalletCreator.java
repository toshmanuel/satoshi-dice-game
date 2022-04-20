package com.qala.dimeji.satoshidice.service;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.*;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MySQLFullPrunedBlockStore;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class WalletCreator {

    @Autowired
    private ConfigFile configFile;

    public Wallet createWallet(NetworkParameters parameters, Script.ScriptType scriptType) throws UnreadableWalletException, BlockStoreException, ExecutionException, InterruptedException, UnknownHostException {
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
        PeerGroup peerGroup = new PeerGroup(parameters, chain);
        peerGroup.addAddress(new PeerAddress(parameters, InetAddress.getLocalHost()));
        chain.addWallet(wallet);
        peerGroup.addWallet(wallet);
        DownloadProgressTracker blockList = new DownloadProgressTracker() {
            @Override
            public void doneDownload() {
                System.out.println("Blockchain download completed!!!");
            }
        };
        peerGroup.start();
        peerGroup.connectToLocalHost();
        peerGroup.startBlockChainDownload(blockList);
        System.out.println("I think I got here----- Line 51");
//        blockList.await();
//        System.out.println("I think I got here----- Line 53");
        peerGroup.waitForPeers(1).get();
        System.out.println("I think I got here----- Line 55");
        Peer peer = peerGroup.getConnectedPeers().get(0);
        System.out.println("I think I got here----- Line 57");
        System.out.println("this is a peer");
        System.out.println(peer);
//        peerGroup.connectTo(new InetSocketAddress("localhost", 18444));

        return wallet;
    }

}
