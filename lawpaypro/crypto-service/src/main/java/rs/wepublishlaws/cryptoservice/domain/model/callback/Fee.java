package rs.wepublishlaws.cryptoservice.domain.model.callback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fee {

    private String type;
    private String currency;
    private String amount;
}
