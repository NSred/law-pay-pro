package com.example.bank.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String pan;
    private String securityCode;
    private String cardHolderName;
    private String expirationDate;
    private Long accountId;
}
