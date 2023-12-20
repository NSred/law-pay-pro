package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
    private String merchantId;
    private String paymentMethod;
    private String subscriptionType;
}
