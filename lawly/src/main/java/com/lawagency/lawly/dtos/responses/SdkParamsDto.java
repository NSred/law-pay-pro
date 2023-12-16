package com.lawagency.lawly.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SdkParamsDto {
    private String qrCode;
    private String paymentAddress;
    private String tag;
}
