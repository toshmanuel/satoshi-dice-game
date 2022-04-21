package com.qala.dimeji.satoshidice.dto.request;

import lombok.Data;

@Data
public class StakeRequest {
    private String address;
    private String transactionHash;
    private Integer numberPicked;
    private String message;
}
