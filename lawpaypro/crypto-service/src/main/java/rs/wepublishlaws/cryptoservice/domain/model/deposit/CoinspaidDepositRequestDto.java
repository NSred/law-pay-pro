package rs.wepublishlaws.cryptoservice.domain.model.deposit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinspaidDepositRequestDto {

    @JsonProperty("foreign_id")
    private String foreignId;
    private String currency;
    @JsonProperty("convert_to")
    private String convertTo;

}
