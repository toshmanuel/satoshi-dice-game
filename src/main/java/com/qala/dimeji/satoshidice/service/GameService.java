package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.UnreadableWalletException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface GameService {
    ApiResponse generateAddressToPay() throws UnreadableWalletException, BlockStoreException;
    Map stake(StakeRequest request) throws NoSuchAlgorithmException, UnreadableWalletException;
    ApiResponse play();
}
