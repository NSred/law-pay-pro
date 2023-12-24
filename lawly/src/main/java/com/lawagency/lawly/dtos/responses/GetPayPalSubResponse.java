package com.lawagency.lawly.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPayPalSubResponse {
    private Long id;
    private String subscriptionId;
    private String subscriptionStatus;
    private String planId;
}
