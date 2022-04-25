package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.UnreadableWalletException;

import java.net.UnknownHostException;

import java.util.concurrent.ExecutionException;

public interface GameService {
    ApiResponse generateAddressToPay() throws UnreadableWalletException, BlockStoreException, ExecutionException, InterruptedException, UnknownHostException;
    ApiResponse stake(StakeRequest request) throws ExecutionException, InterruptedException, InsufficientMoneyException;
}
