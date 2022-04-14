package com.qala.dimeji.satoshidice.dto.response;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private String status;
    private int code;
}
