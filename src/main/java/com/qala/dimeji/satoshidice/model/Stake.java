package com.qala.dimeji.satoshidice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Getter
@Setter

public class Stake {
    @Id
    private String id;
    private String address;
    private BigDecimal stakeAmount;
    private LocalDateTime dateCreated;
    private StakeStatus status;
}
