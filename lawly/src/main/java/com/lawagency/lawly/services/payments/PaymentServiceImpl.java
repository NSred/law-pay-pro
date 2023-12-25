package com.lawagency.lawly.services.payments;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.dtos.responses.*;
import com.lawagency.lawly.external.common.PspPaymentRequest;
import com.lawagency.lawly.external.httpclient.HttpClientService;
import com.lawagency.lawly.handler.exceptions.ProccessPaymentException;
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
    public PaymentResponse processPayment(PaymentRequest request) {
        try{
            Offer offer = offerService.findOne(request.getOfferId());
            PspPaymentRequest pspRequest = PaymentMapper.mapToPspPaymentRequest(request, offer.getPrice());
            return httpClientService.sendProcessPaymentRequest(pspRequest);
        } catch (Exception e){
            throw new ProccessPaymentException(e.getMessage());
        }
    }

    @Override
    public GetPayPalSubResponse getPayPalSub(GetPayPalSubRequest request) {
        return httpClientService.getPayPalSub(request);
    }

    @Override
    public boolean updateSub(UpdatePayPalSubRequest request) {
        return httpClientService.updateSub(request);
    }

    @Override
    public boolean cancelSub(UpdatePayPalSubRequest request) {
        return httpClientService.cancelSub(request);
    }

    @Override
    public QrCodeResponseDto payQr(QrCodeRequestDto request) {
        return httpClientService.payQr(request);
    }
}
