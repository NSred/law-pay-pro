package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodServiceDto {
    private long id;
    private String name;
    private String imageUrl;
    private boolean subscribed;
}
