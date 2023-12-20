package com.lawagency.lawly.services.subscriptions;

import com.lawagency.lawly.dtos.responses.GetPaymentMethodsResponse;

public interface SubscriptionService {
    GetPaymentMethodsResponse getPaymentMethodsByMerchant();
}
