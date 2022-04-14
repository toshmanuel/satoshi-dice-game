package com.qala.dimeji.satoshidice;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class SatoshiDiceApplication {

    public static void main(String[] args){
        SpringApplication.run(SatoshiDiceApplication.class, args);
//        Wallet wallet = Wallet.createDeterministic(MainNetParams.get(), Script.ScriptType.P2WPKH);
////        DeterministicSeed seed = new DeterministicSeed("", null, "", 1L);
////        Wallet wallet2 = Wallet.fromSeed(MainNetParams.get(), , Script.ScriptType.P2WPKH)
//        System.out.println();
//        log.info("wallet info ->{}", wallet);
//        log.info("wallet seed info ->{}", wallet.getKeyChainSeed().getMnemonicCode());
//        log.info("wallet seed info ->{}", wallet.getKeyChainSeed().getCreationTimeSeconds());
//        return wallet;
    }

}
