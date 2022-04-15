package com.qala.dimeji.satoshidice.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties("wallet")
@NoArgsConstructor
@Getter
@Setter
public class ConfigFile {

    @Value("${wallet.mnemonicCode}")
    private String mnemonicCode;

    @Value("${wallet.creationTime}")
    private long creationTime;

    @Value("${ledger.host}")
    private String ledgerHost;

    @Value("${ledger.name}")
    private String ledgerName;

    @Value("${ledger.user}")
    private String ledgerUser;

    @Value("${ledger.pass}")
    private String ledgerPassword;
}