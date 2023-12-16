package rs.wepublishlaws.cryptoservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinsPaidErrorResponse {
    private ErrorResponseDto errors;
    private String errorCode;
}
