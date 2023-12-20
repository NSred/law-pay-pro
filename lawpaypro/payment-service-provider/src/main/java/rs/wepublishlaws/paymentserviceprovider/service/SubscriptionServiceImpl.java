package rs.wepublishlaws.paymentserviceprovider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.model.Subscription;
import rs.wepublishlaws.paymentserviceprovider.repository.SubscriptionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;
    private final MerchantService merchantService;

    public Collection<String> getPaymentMethodsByMerchant(String merchantId) {
        return subscriptionRepository.findPaymentMethodsByMerchantId(merchantId);
    }

    @Override
    public Subscription getSubscriptionByMerchant(String merchantId) {
        return subscriptionRepository.findSubscriptionByMerchantId(merchantId);
    }

    @Override
    public void makeSubscription(String merchantId, String paymentMethod, String subscriptionType) throws JsonProcessingException {
        Merchant merchant = merchantService.findMerchantByMerchantId(merchantId);
        if (merchant == null)
            throw new RuntimeException();
        Collection<String> paymentMethodsByMerchant = getPaymentMethodsByMerchant(merchant.getMerchantId());
        List<String> paymentMethods = new ArrayList<>(paymentMethodsByMerchant);
        if(subscriptionType.equals("SUBSCRIBE")){
            paymentMethods.add(paymentMethod);
        }else {
            paymentMethods.remove(paymentMethod);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String paymentMethodsJson = objectMapper.writeValueAsString(paymentMethods);
        subscriptionRepository.updatePaymentMethods(merchant.getMerchantId(), paymentMethodsJson);
    }

    @Override
    public void createNewSubscription(String merchantId) {
        subscriptionRepository.insertSubscription(merchantId);
    }
}
