package com.lawagency.lawly.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentMethodsResponse {
    private Collection<String> paymentMethods;
    private boolean isError;
    private String reason;
}
