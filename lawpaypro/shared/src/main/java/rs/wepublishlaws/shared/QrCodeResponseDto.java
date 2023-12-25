package rs.wepublishlaws.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeResponseDto {
    private String url;
    private Long acquirerOrderId;
    private LocalDateTime acquirerTimestamp;
    private String merchantOrderId;
    private String paymentId;
}
