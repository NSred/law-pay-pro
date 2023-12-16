package rs.wepublishlaws.cryptoservice.domain.model.callback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyReceived {

    private String currency;
    private String amount;
    private String amount_minus_fee;
}
