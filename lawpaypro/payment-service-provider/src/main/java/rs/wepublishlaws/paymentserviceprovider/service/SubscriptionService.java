package rs.wepublishlaws.paymentserviceprovider.service;

import java.util.Collection;

public interface SubscriptionService {
    Collection<String> getPaymentMethodsByMerchant(String merchantId);
}
