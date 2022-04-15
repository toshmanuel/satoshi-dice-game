package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.SegwitAddress;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.KeyChain;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private WalletCreator walletCreator;

    @Override
    public ApiResponse stake(StakeRequest request){
        return null;
    }

    @Override
    public ApiResponse generateAddressToPay() throws UnreadableWalletException, BlockStoreException {
        Wallet wallet = walletCreator.createWallet(MainNetParams.get(), Script.ScriptType.P2WPKH);
        Address address = wallet.freshReceiveAddress();
        Address currentAddress = wallet.currentReceiveAddress();
        log.info("address seed info ->{}", address);
        log.info("current seed info ->{}", currentAddress);
        return new ApiResponse(String.format("Pls send the amount of bitcoin you want to stake to: %s", address.toString()), "success", 200);
    }

    @Override
    public ApiResponse play() {
        SecureRandom random = new SecureRandom();
        int randomNumber = 1 + random.nextInt(100);
//        if ()
        return null;
    }
}
