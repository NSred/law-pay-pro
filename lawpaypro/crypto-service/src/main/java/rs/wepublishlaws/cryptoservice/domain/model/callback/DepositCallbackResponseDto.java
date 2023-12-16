package rs.wepublishlaws.cryptoservice.domain.model.callback;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepositCallbackResponseDto {

    private Integer id;
    private String type;
    private CryptoAddress crypto_address;
    private CurrencySent currency_sent;
    private CurrencyReceived currency_received;
    private List<Transaction> transactions;
    private List<Fee> fees;
    private String error;
    private TransactionStatus status;
}
