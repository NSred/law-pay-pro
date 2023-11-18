package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
}
