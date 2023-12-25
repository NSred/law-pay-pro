package com.example.bank.Service;

import com.example.bank.DTO.QRCodeDTO;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRService {

    public String qrCodeToString(String Base64QR) throws NotFoundException {
        byte[] imageBytes = Base64.getDecoder().decode(Base64QR);

        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            if (image != null) {
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

                Map<DecodeHintType, Object> hints = new HashMap<>();
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

                Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hints);
                String decodedText = qrCodeResult.getText();

                System.out.println("Decoded text: " + decodedText);
                return decodedText;
            } else {
                System.out.println("Failed to read the image.");
            }
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return "decodedText";
    }

    public Boolean validateQrCode(String qrCode){
        return true;
    }

    public QRCodeDTO toQRCodeDTO(String qrCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        try {
            QRCodeDTO qrCodeDTO = objectMapper.readValue(qrCode, QRCodeDTO.class);

            return qrCodeDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
