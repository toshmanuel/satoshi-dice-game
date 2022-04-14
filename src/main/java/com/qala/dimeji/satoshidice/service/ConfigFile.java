package com.qala.dimeji.satoshidice.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties("wallet")
@NoArgsConstructor
@Getter
@Setter
public class ConfigFile {

    private String mnemonicCode;

    private long creationTime;
}