package rs.wepublishlaws.paymentserviceprovider.service;

import rs.wepublishlaws.paymentserviceprovider.dto.PaymentMethodServiceDto;
import rs.wepublishlaws.paymentserviceprovider.model.PaymentMethodService;

import java.util.List;

public interface PaymentMethodServiceService {
    List<PaymentMethodServiceDto> getAllByMerchant(String merchantId);
    List<PaymentMethodService> getAll();
}
