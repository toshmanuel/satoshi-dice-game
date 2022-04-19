package com.qala.dimeji.satoshidice.service;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Address;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private WalletCreator walletCreator;

    @Override
    public Map stake(StakeRequest request){
        String url = "https://api.blockcypher.com/v1/btc/test3/txs/"+ request.getTransactionHash();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Map> res = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        Map response = res.getBody();
        System.out.println(response);
        return response;
    }

    @Override
    public ApiResponse generateAddressToPay() throws UnreadableWalletException, BlockStoreException {
        Wallet wallet = walletCreator.createWallet(RegTestParams.get(), Script.ScriptType.P2WPKH);
        Address address = wallet.freshReceiveAddress();
        Address currentAddress = wallet.currentReceiveAddress();
        log.info("address seed info ->{}", address);
        log.info("current seed info ->{}", currentAddress);
        log.info("current wallet balance ->{}", wallet.getBalance(Wallet.BalanceType.AVAILABLE));
        log.info("current wallet balance ->{}", wallet.getBalance(Wallet.BalanceType.ESTIMATED));
        log.info("current wallet balance ->{}", wallet.getBalance(Wallet.BalanceType.AVAILABLE_SPENDABLE));
        log.info("current wallet balance ->{}", wallet.getBalance(Wallet.BalanceType.ESTIMATED_SPENDABLE));
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
