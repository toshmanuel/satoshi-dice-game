package com.qala.dimeji.satoshidice.service;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class GameServiceImplTest {

    @Autowired
    GameService gameService;

    @Test
    void stake() throws UnreadableWalletException, NoSuchAlgorithmException {
      gameService.stake(null);
    }

    @Test
    void play() {
    }
}