package rs.wepublishlaws.paymentserviceprovider.service;

import rs.wepublishlaws.paymentserviceprovider.model.Merchant;

public interface MerchantService {
    public boolean existsMerchantByMerchantId(String merchantId);
    public String generateApiKey(String merchantId, String merchantPassword);
    Merchant findMerchantByApiKey(String merchantId);
    Merchant findMerchantByMerchantId(String merchantId);
}
