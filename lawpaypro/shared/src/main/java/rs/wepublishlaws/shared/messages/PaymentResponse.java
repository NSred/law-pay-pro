package rs.wepublishlaws.shared.messages;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PaymentResponse {
    public String message;
    public LocalDateTime dateTime;
}
