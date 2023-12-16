package rs.wepublishlaws.paymentserviceprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.repository.SubscriptionRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;

    public Collection<String> getPaymentMethodsByMerchant(String merchantId) {
        return subscriptionRepository.findPaymentMethodsByMerchantId(merchantId);
    }
}
