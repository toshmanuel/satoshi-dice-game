package com.qala.dimeji.satoshidice.dto.request;

import lombok.Data;

@Data
public class StakeRequest {
    private String address;
    private Double stakeAmount;
    private Integer number;
}
