package rs.wepublishlaws.cryptoservice.domain.model.deposit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import rs.wepublishlaws.cryptoservice.domain.model.CoinsPaidErrorResponse;

@Getter
@Setter
public class CoinspaidDepositResponseDto extends CoinsPaidErrorResponse {

    @JsonProperty("data")
    private CoinspaidDepositResponseData data;
}
