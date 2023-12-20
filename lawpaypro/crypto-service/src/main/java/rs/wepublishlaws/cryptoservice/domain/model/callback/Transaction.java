package rs.wepublishlaws.cryptoservice.domain.model.callback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private String id;
    private String currency;
    private String currency_to;
    private TransactionType transaction_type;
    private String type;
    private String address;
    private String tag;
    private String amount;
    private String amount_to;
    private String txid;
    private String riskscore;
    private String confirmations;
}
