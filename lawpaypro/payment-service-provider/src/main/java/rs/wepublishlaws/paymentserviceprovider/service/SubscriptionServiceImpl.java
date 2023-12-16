package rs.wepublishlaws.paymentserviceprovider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.model.Subscription;
import rs.wepublishlaws.paymentserviceprovider.repository.SubscriptionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;

    public Collection<String> getPaymentMethodsByMerchant(String merchantId) {
        return subscriptionRepository.findPaymentMethodsByMerchantId(merchantId);
    }

    @Override
    public void makeSubscription(Merchant merchant, String paymentMethod, String subscriptionType) throws JsonProcessingException {
        Collection<String> paymentMethodsByMerchant = getPaymentMethodsByMerchant(merchant.getMerchantId());
        List<String> paymentMethods = new ArrayList<>(paymentMethodsByMerchant);
        if(subscriptionType.equals("SUBSCRIBE")){
            paymentMethods.add(paymentMethod);
        }else {
            paymentMethods.remove(paymentMethod);
        }
//        Subscription subscription = subscriptionRepository.findById(merchant.getId())
//                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));;
//        subscription.setPaymentMethods(paymentMethods);
//        subscriptionRepository.save(subscription);
        ObjectMapper objectMapper = new ObjectMapper();
        String paymentMethodsJson = objectMapper.writeValueAsString(paymentMethods);
        subscriptionRepository.updatePaymentMethods(merchant.getId(), paymentMethodsJson);
    }
}
