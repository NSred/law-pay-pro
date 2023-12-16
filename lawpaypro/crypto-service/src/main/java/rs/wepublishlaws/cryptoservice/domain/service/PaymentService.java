package rs.wepublishlaws.cryptoservice.domain.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import rs.wepublishlaws.cryptoservice.domain.configuration.CertConfig;
import rs.wepublishlaws.cryptoservice.domain.configuration.HttpConfig;
import rs.wepublishlaws.cryptoservice.domain.model.deposit.CoinspaidDepositRequestDto;
import rs.wepublishlaws.cryptoservice.domain.model.deposit.CoinspaidDepositResponseDto;
import org.springframework.web.client.RestTemplate;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.messages.requests.PaymentStatusNotify;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);
    private static final String DEPOSIT = "/addresses/take";
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final CertConfig certConfig;
    private final HttpConfig httpConfig;

    public CoinspaidDepositResponseDto initiatePayment(
            final String apiUrl,
            final CoinspaidDepositRequestDto transactionRequestDto
    ) throws Exception {
        try {
            final Map<String, String> depositRequest = prepareCoinsPaidDepositRequest(certConfig.privateKey(), transactionRequestDto);

            return restTemplate.postForObject(
                    apiUrl + DEPOSIT,
                    new HttpEntity<>(
                            depositRequest.get("requestBody"),
                            generateRequestHeaders(depositRequest.get("signature"))
                    ),
                    CoinspaidDepositResponseDto.class
            );
        } catch (final HttpClientErrorException exception) {
            final CoinspaidDepositResponseDto errorResponse = gson.fromJson(
                    exception.getResponseBodyAsString(),
                    CoinspaidDepositResponseDto.class
            );
            errorResponse.setErrorCode(String.valueOf(exception.getRawStatusCode()));
            LOGGER.error(
                    "Exception occurred while trying to execute prepare deposit, response from provider \n{}",
                    gson.toJson(errorResponse)
            );
            return errorResponse;
        }
    }

    public void sendNotification(final PaymentStatusNotify responseDto) throws Exception {
        ResponseEntity<PaymentStatusNotify> responseEntity;
        try {
            responseEntity = restTemplate.postForEntity(
                    httpConfig.paymentNotifyUrl() + String.format("/%s", responseDto.getPaymentId()),
                    responseDto,
                    PaymentStatusNotify.class
            );
        } catch (final Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (responseEntity.getStatusCodeValue() != 200) {
            throw new ResponseStatusException(responseEntity.getStatusCode());
        }
    }


    private Map<String, String> prepareCoinsPaidDepositRequest(
            final String apiSecret,
            final CoinspaidDepositRequestDto transactionRequestDto
    ) throws JsonProcessingException {
        String requestBody = createRequestBody(apiSecret, transactionRequestDto);
        String signature = calculateHmacSha512(requestBody, apiSecret);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("requestBody", requestBody);
        responseMap.put("signature", signature);
        return responseMap;
    }

    private String createRequestBody(
            final String apiSecret,
            final CoinspaidDepositRequestDto transactionRequestDto
    ) throws JsonProcessingException {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put(certConfig.publicKey(), apiSecret);
        paramsMap.put("currency", transactionRequestDto.getCurrency());
        paramsMap.put("convert_to", transactionRequestDto.getConvertTo());
        paramsMap.put("foreign_id", transactionRequestDto.getForeignId());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(paramsMap);
        return requestBody;
    }

    private String calculateHmacSha512(String data, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes());
            Hex hex = new Hex();
            byte[] encodedBytes = hex.encode(hmacBytes);

            return new String(encodedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpHeaders generateRequestHeaders(
            final String signature
    ) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-Processing-Key", certConfig.publicKey());
        headers.add("X-Processing-Signature", signature);

        final MediaType contentType = new MediaType("application", "json");
        headers.setContentType(contentType);

        return headers;
    }

}
