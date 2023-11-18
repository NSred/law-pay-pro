package pcc.DTO;

import java.time.LocalDateTime;

public class PCCRequestDTO {

    private String pan;
    private String securityCode;
    private String cardHolderName;
    private String expirationDate;
    private Double amount;
    private Long acquirer_order_id;
    private LocalDateTime acquirer_timestamp;
}
