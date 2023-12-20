package rs.wepublishlaws.cryptoservice.domain.model.deposit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinspaidDepositResponseData {

    private Long id;
    private String currency;
    @JsonProperty("convert_to")
    private String convertTo;
    private String address;
    private String tag;
    @JsonProperty("foreign_id")
    private String foreignId;
}
