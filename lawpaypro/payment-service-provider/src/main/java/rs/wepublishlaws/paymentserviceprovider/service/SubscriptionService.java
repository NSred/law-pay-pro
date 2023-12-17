package rs.wepublishlaws.paymentserviceprovider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.model.Subscription;

import java.util.Collection;

public interface SubscriptionService {
    Collection<String> getPaymentMethodsByMerchant(String merchantId);
    Subscription getSubscriptionByMerchant(String merchantId);
    void makeSubscription(String merchantId, String paymentMethod, String subscriptionType) throws JsonProcessingException;
    void createNewSubscription(String merchantId);
}
