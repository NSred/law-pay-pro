package com.lawagency.lawly.external.httpclient;

import com.lawagency.lawly.dtos.responses.GetPaymentMethodsResponse;
import com.lawagency.lawly.dtos.responses.PaymentResponse;
import com.lawagency.lawly.external.common.PspPaymentRequest;
import com.lawagency.lawly.handler.exceptions.HttpClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HttpClientService {
    private final RestTemplate restTemplate;
    @Value("${lawly.psp-url}")
    private String pspUrl;
    @Value("${lawly.psp-api-key}")
    private String apiKey;

    public PaymentResponse sendProcessPaymentRequest(PspPaymentRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("API-Key", apiKey);
            HttpEntity<PspPaymentRequest> message = new HttpEntity<>(request, headers);
            return restTemplate.postForObject(pspUrl + "/payments/process", message, PaymentResponse.class);
        } catch (Exception e){
            throw new HttpClientException(e.getMessage());
        }
    }

    public GetPaymentMethodsResponse getPaymentMethodsResponse(String merchantId){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("API-Key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String url = pspUrl + "/subscriptions?merchantId=" + merchantId;
            ResponseEntity<GetPaymentMethodsResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    GetPaymentMethodsResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new HttpClientException(e.getMessage());
        }
    }
}
