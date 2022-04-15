package com.qala.dimeji.satoshidice.controller;

import com.qala.dimeji.satoshidice.dto.response.ApiResponse;
import com.qala.dimeji.satoshidice.service.GameService;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/server-address")
    public ResponseEntity<?> address(){
        try {
            ApiResponse response = gameService.generateAddressToPay();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnreadableWalletException | BlockStoreException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
