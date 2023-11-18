package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String merchantId;
    private String merchantPassword;
}
