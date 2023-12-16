package rs.wepublishlaws.cryptoservice.domain.model.callback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencySent {

    private String currency;
    private String amount;
}
