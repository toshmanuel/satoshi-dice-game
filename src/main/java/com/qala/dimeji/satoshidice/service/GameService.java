package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import org.bitcoinj.wallet.UnreadableWalletException;

import java.security.NoSuchAlgorithmException;

public interface GameService {
    ApiResponse stake(StakeRequest request) throws NoSuchAlgorithmException, UnreadableWalletException;
    ApiResponse play();
}
