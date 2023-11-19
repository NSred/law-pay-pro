package com.lawagency.lawly.external.httpclient;

import com.lawagency.lawly.external.common.PspPaymentRequest;
import com.lawagency.lawly.handler.exceptions.HttpClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    public String sendRequest(PspPaymentRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("API-Key", apiKey);
            HttpEntity<PspPaymentRequest> message = new HttpEntity<>(request, headers);
            return restTemplate.postForObject(pspUrl, message, String.class);
        } catch (Exception e){
            throw new HttpClientException(e.getMessage());
        }

    }
}
