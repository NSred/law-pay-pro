package com.lawagency.lawly.services.payments;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.external.common.PspPaymentRequest;
import com.lawagency.lawly.external.httpclient.HttpClientService;
import com.lawagency.lawly.model.Offer;
import com.lawagency.lawly.model.mappers.PaymentMapper;
import com.lawagency.lawly.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final OfferService offerService;
    private final HttpClientService httpClientService;

    @Override
    public String processPayment(PaymentRequest request) {
        Offer offer = offerService.findOne(request.getOfferId());
        PspPaymentRequest pspRequest = PaymentMapper.mapToPspPaymentRequest(request, offer.getPrice());
        return httpClientService.sendRequest(pspRequest);
    }
}
