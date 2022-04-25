package com.qala.dimeji.satoshidice.controller;

import com.qala.dimeji.satoshidice.dto.request.StakeRequest;
import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import com.qala.dimeji.satoshidice.service.GameService;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/server-address")
    public ResponseEntity<?> address() {
        try {
            ApiResponse response = gameService.generateAddressToPay();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnknownHostException | UnreadableWalletException | BlockStoreException | ExecutionException | InterruptedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stake")
    public ResponseEntity<?> stake(StakeRequest request) {
        try {
            return new ResponseEntity<>(gameService.stake(request), HttpStatus.OK);
        } catch (InsufficientMoneyException | ExecutionException | InterruptedException | IllegalStateException e) {
            return new ResponseEntity<>(new ApiResponse(e.getLocalizedMessage(), "Failed", 400), HttpStatus.BAD_REQUEST);
        }
    }
}
