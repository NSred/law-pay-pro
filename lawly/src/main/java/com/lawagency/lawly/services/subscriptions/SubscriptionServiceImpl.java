package com.lawagency.lawly.services.subscriptions;

import com.lawagency.lawly.dtos.responses.GetPaymentMethodsResponse;
import com.lawagency.lawly.external.httpclient.HttpClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final HttpClientService httpClientService;
    @Value("${merchantId}")
    private String merchantId;


    @Override
    public GetPaymentMethodsResponse getPaymentMethodsByMerchant() {
        return httpClientService.getPaymentMethodsResponse(merchantId);
    }
}
