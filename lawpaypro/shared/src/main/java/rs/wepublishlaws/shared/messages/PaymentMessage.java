package rs.wepublishlaws.shared.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentMessage {
    public String merchantId;
    public String merchantPassword;
    public Double amount;
    public Integer merchantOrderId;
    public LocalDateTime merchantTimestamp;
    public String successUrl;
    public String failedUrl;
    public String errorUrl;
}
