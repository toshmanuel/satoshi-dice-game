package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private WalletCreator walletCreator;

    @Override
    public ApiResponse stake(StakeRequest request) throws UnreadableWalletException {

        return null;
    }

    @Override
    public ApiResponse play() {
        SecureRandom random = new SecureRandom();
        int randomNumber = 1 + random.nextInt(100);
//        if ()
        return null;
    }
}
