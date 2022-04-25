package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.*;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.WalletTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private Wallet wallet;

    @Autowired
    private Peer peer;

    private final Map<Integer, String> transactionHashMap = new HashMap<>();
    private int count = 0;

    @Override
    public ApiResponse generateAddressToPay() {
        Address address = wallet.freshReceiveAddress();
        System.out.println(wallet.getTransactionsByTime());
        System.out.println(wallet.getWatchedAddresses());
        System.out.println(wallet.toString());
        return new ApiResponse(String.format("Pls send the amount of bitcoin you want to stake to: %s", address.toString()), "success", 200);
    } /*functioning properly*/

    @Override
    public ApiResponse stake(StakeRequest request) throws InsufficientMoneyException {
        if (request.getNumberPicked() > 100 || request.getNumberPicked() < 1) {
            throw new IllegalStateException("Number cannot be less than 1 or greater than 100");
        }
        Transaction tx = getTransaction(request.getTransactionHash());
        if (tx.isPending()) {
            throw new IllegalStateException("Pls wait for at least 1 block confirmation");
        }
        System.out.println(wallet.getTotalReceived().toString());
        System.out.println(wallet.getBalance(Wallet.BalanceType.AVAILABLE).toString());
        System.out.println(wallet.getBalance(Wallet.BalanceType.AVAILABLE_SPENDABLE).toString());
        System.out.println(wallet.getBalance(Wallet.BalanceType.ESTIMATED).toString());
        System.out.println(wallet.getBalance(Wallet.BalanceType.AVAILABLE_SPENDABLE).toString());
        if (isTransactionPresent(tx)) {
            System.out.println(wallet.getTotalReceived().toString());
            if (transactionHashMap.containsValue(request.getTransactionHash())) {
                throw new IllegalStateException("You want to fraud us right!!!");
            }
            transactionHashMap.put(++count, request.getTransactionHash());
            SecureRandom random = new SecureRandom();
            int number = 1 + random.nextInt(100);
            if (request.getNumberPicked() /*e.g 50*/ <= number /*e.g 52*/) { /*win*/

                long amount = tx.getInputSum().getValue() + tx.getInputSum().getValue() / 2;
                payPlayer(request.getAddress(), amount);
                return new ApiResponse(
                        "You have won, kindly wait for approximately 1hr to get you funded",
                        "success",
                        200
                );
            } else {
                return new ApiResponse(
                        "You have lost, kindly try your luck again",
                        "success",
                        200
                );
            }

        }
        throw new IllegalStateException("Invalid transaction hash");
    }

    private boolean isTransactionPresent(Transaction tx) {
        Optional<Transaction> transactionOptional = wallet.getTransactions(true).stream()
                .filter(t -> t.equals(tx))
                .findAny();

        return transactionOptional.isPresent();
    }

    private Transaction getTransaction(String transactionHash) {
        Optional<Transaction> transactionOptional = wallet.getTransactions(true).stream()
                .filter(t -> t.getHashAsString().equals(transactionHash))
                .findAny();

        return transactionOptional.orElseThrow();
    }

    private void payPlayer(String receiverAddress, long amount) throws InsufficientMoneyException {
        Address address = Address.fromString(RegTestParams.get(), receiverAddress);
        SendRequest request = SendRequest.to(address, Coin.valueOf(amount));
        wallet.sendCoins(peer, request);
    }
}

// for getting transaction
/*
System.out.println(wallet.toString());
        Sha256Hash txHash = Sha256Hash.wrap(transactionHash);
        ListenableFuture<Transaction> futtrx = peer.getPeerMempoolTransaction(txHash);
        System.out.println("Waiting for node to send us the requested transaction: " + txHash);
        System.out.println(futtrx.get().toString());*/


// for knowing if a transaction is present in the mempool

    /*System.out.println("The following are the transactions present");
        wallet.getWalletTransactions().forEach(e -> System.out.println(e.getTransaction()));
        wallet.getWalletTransactions().forEach(e -> System.out.println(e.getTransaction().equals(tx)));
        System.out.println("This is line 90");
        for (WalletTransaction transaction : wallet.getWalletTransactions()) {
            if(transaction.getTransaction().getTxId() == tx.getTxId()){
                return true;
            }
        }
        return false;*/