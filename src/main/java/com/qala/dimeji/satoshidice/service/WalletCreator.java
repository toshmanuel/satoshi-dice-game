package com.qala.dimeji.satoshidice.service;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletCreator {

    @Autowired
    private ConfigFile configFile;

    public Wallet createWallet(NetworkParameters parameters, Script.ScriptType scriptType) throws UnreadableWalletException {
        DeterministicSeed seed = new DeterministicSeed(configFile.getMnemonicCode(), null, "", configFile.getCreationTime());
        Wallet wallet = Wallet.fromSeed(parameters, seed, scriptType);

        log.info("wallet info ->{}", wallet);
        log.info("wallet seed info ->{}", wallet.getKeyChainSeed().getMnemonicCode());
        log.info("wallet seed info ->{}", wallet.getKeyChainSeed().getCreationTimeSeconds());

        return wallet;
    }

}
