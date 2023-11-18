package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
