package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscritionRequest {

    private String paymentMethod;
    private String subscriptionType;
}
