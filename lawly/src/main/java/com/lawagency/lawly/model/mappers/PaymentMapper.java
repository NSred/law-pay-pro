package com.lawagency.lawly.model.mappers;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.external.common.PspPaymentRequest;
import com.lawagency.lawly.model.enums.PaymentType;

public class PaymentMapper {
    public static PspPaymentRequest mapToPspPaymentRequest(PaymentRequest request, double amount){
        PaymentType type = PaymentType.valueOf(request.getPaymentType());
        return new PspPaymentRequest(amount, type);
    }
}
