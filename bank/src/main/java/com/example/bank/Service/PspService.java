package com.example.bank.Service;

import com.example.bank.DTO.PSPResponseDTO;
import com.example.bank.DTO.PaymentDTO;
import com.example.bank.DTO.responses.PaymentResponse;
import com.example.bank.DTO.responses.SdkParamsDto;
import com.example.bank.Model.Enum.Url;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PspService {
    private final MerchantService merchantService;
    private final TransactionService transactionService;
    @Value("${bank.payment-url}")
    private String paymentUrl;
    @Value("${bank.fail-url}")
    private String failUrl;
    @Value("${bank.success-url}")
    private String successUrl;
    @Value("${bank.error-url}")
    private String errorUrl;

    public PaymentResponse processPayment(PaymentDTO paymentDTO) {
        boolean isValidMerchant  = merchantService.existsByMerchantId(paymentDTO.getMerchantId());
        if (isValidMerchant){
            String paymentId = UUID.randomUUID().toString();
            transactionService.createBaseTransaction(paymentDTO, paymentId);
            return new PaymentResponse(paymentUrl, paymentId, null, null, null);
        }
        return new PaymentResponse(failUrl, null, null, null, null);
    }
    public PSPResponseDTO response(Url url){
        if(url==Url.SUCCESSFUL)
            return new PSPResponseDTO(successUrl);
        else if(url == Url.ERROR)
            return new PSPResponseDTO(errorUrl);
        else
            return new PSPResponseDTO(failUrl);

    }

    public PaymentResponse processPaymentQR(PaymentDTO paymentDTO) throws IOException, WriterException {
        boolean isValidMerchant  = merchantService.existsByMerchantId(paymentDTO.getMerchantId());
        if (isValidMerchant){
            String paymentId = UUID.randomUUID().toString();
            transactionService.createBaseTransaction(paymentDTO, paymentId);
            String K = "PR";
            String V = "01";
            String C = "1";
            String R = "12345";
            String N = "Lawly, Balzakova 13";
            String I = "$"+paymentDTO.getAmount().toString();
            String P = "Miki Miljkovic, Župska 15, Beograd 11000";
            String SF = "289";
            String S = "Transakcije po nalogu građana";
            String RO = "97163220000111111111000";

            String jsonData = generateJson(K, V, C, R, N, I, P, SF, S, RO);
            byte[] qrCode = generateQRCode(jsonData);
            String base64QRCode = Base64.encodeBase64String(qrCode);
            final SdkParamsDto sdkParamsDto = new SdkParamsDto();
            sdkParamsDto.setQrCode(base64QRCode);
            return new PaymentResponse(paymentUrl, paymentId, sdkParamsDto, null, null);
        }
        return new PaymentResponse(failUrl, null,null, null, null);
    }

    private static String generateJson(String K, String V, String C, String R, String N, String I, String P, String SF, String S, String RO) {
        return "{\n" +
                "    \"K\": \"" + K + "\",\n" +
                "    \"V\": \"" + V + "\",\n" +
                "    \"C\": \"" + C + "\",\n" +
                "    \"R\": \"" + R + "\",\n" +
                "    \"N\": \"" + N + "\",\n" +
                "    \"I\": \"" + I + "\",\n" +
                "    \"P\": \"" + P + "\",\n" +
                "    \"Sf\": \"" + SF + "\",\n" +
                "    \"S\": \"" + S + "\",\n" +
                "    \"Ro\": \"" + RO + "\"\n" +
                "}";
    }
    public byte[] generateQRCode(String data) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        return outputStream.toByteArray();
    }


}
