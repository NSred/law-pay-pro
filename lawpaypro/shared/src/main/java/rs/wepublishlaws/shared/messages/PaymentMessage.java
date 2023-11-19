package rs.wepublishlaws.shared.messages;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {
    public String merchantId;
    public String merchantPassword;
    public Double amount;
    public UUID merchantOrderId;
    public LocalDateTime merchantTimestamp;
    public String successUrl;
    public String failedUrl;
    public String errorUrl;
}
