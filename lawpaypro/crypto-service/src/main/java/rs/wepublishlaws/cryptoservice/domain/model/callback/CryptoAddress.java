package rs.wepublishlaws.cryptoservice.domain.model.callback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoAddress {

    private Integer id;
    private String currency;
    private String convert_to;
    private String address;
    private String tag;
    private String foreign_id;
}
