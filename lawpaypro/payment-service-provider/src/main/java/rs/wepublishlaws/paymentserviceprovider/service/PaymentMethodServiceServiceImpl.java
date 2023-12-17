package rs.wepublishlaws.paymentserviceprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.dto.PaymentMethodServiceDto;
import rs.wepublishlaws.paymentserviceprovider.model.PaymentMethodService;
import rs.wepublishlaws.paymentserviceprovider.model.Subscription;
import rs.wepublishlaws.paymentserviceprovider.repository.PaymentMethodServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceServiceImpl implements PaymentMethodServiceService{
    private final SubscriptionService subscriptionService;
    private final PaymentMethodServiceRepository repository;

    @Override
    public List<PaymentMethodServiceDto> getAllByMerchant(String merchantId) {
        var dtos = new ArrayList<PaymentMethodServiceDto>();
        Subscription sub = subscriptionService.getSubscriptionByMerchant(merchantId);
        if (sub == null) {
            subscriptionService.createNewSubscription(merchantId);
            sub = new Subscription();
            sub.setPaymentMethods(new ArrayList<>());
        }
        List<PaymentMethodService> methods = repository.findAll();
        for (PaymentMethodService method : methods) {
            PaymentMethodServiceDto dto = new PaymentMethodServiceDto();
            dto.setId(method.getId());
            dto.setName(method.getName());
            dto.setImageUrl(method.getImageUrl());
            dto.setSubscribed(sub.getPaymentMethods().contains(method.getName()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<PaymentMethodService> getAll() {
        return repository.findAll();
    }
}
