package rs.wepublishlaws.shared.messages;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {
//    public String merchantId;
//    public String merchantPassword;
//    public Double amount;
//    public Integer merchantOrderId;
//    public LocalDateTime merchantTimestamp;
//    public String successUrl;
//    public String failedUrl;
//    public String errorUrl;
    public String service;
}
