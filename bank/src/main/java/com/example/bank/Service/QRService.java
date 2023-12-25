package com.example.bank.Service;

import com.example.bank.DTO.QRCodeDTO;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class QRService {

    public String qrCodeToString(String Base64QR){
        byte[] imageBytes = Base64.getDecoder().decode(Base64QR);
        String decodedImage = new String(imageBytes);
        return decodedImage;


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
