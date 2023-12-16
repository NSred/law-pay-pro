package rs.wepublishlaws.paymentserviceprovider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;

import java.util.Collection;

public interface SubscriptionService {
    Collection<String> getPaymentMethodsByMerchant(String merchantId);

    void makeSubscription(Merchant merchant, String paymentMethod, String subscriptionType) throws JsonProcessingException;
}
