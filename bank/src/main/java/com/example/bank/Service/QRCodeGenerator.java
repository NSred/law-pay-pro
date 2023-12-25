package com.example.bank.Service;

import com.example.bank.DTO.QRCodeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public static void main(String[] args) {
        String K = "PR";
        String V = "01";
        String C = "1";
        String R = "845000000040484987";
        String N = "JP EPS BEOGRAD, BALKANSKA 13";
        String I = "RSD3596,13";
        String P = "MRĐO MAČKATOVIĆ, ŽUPSKA 13, BEOGRAD 6";
        String SF = "189";
        String S = "UPLATA PO RAČUNU ZA EL. ENERGIJU";
        String RO = "97163220000111111111000";

        String jsonData = generateJson(K, V, C, R, N, I, P, SF, S, RO);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);


        try {
            QRCodeDTO qrCodeDTO = objectMapper.readValue(jsonData, QRCodeDTO.class);

            // Now, qrCodeDTO contains the deserialized data from the JSON string
            System.out.println("K: " + qrCodeDTO.getK());
            // ... similarly for other fields

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String jsonDataa = generateJson(K, V, C, R, N, I, P, SF, S, RO);
            //generateQRCode(jsonData, "qrcode.png");
            //System.out.println("QR code generated successfully!");
            String base64QRCode = generateBase64QRCode(jsonData);
            //System.out.println("Base64 encoded QR code: " + base64QRCode);
           // byte[] qrCode = generateQRCode1(jsonData);
            //System.out.println(qrCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static void generateQRCode(String jsonData, String filePath) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(jsonData, BarcodeFormat.QR_CODE, 200, 200, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
    private static String generateBase64QRCode(String jsonData) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(jsonData, BarcodeFormat.QR_CODE, 200, 200, hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        byte[] imageBytes = outputStream.toByteArray();
        return Base64.encodeBase64String(imageBytes);
    }
    public static byte[] generateQRCode1(String data) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        return outputStream.toByteArray();
    }
}

