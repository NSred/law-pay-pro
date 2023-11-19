package rs.wepublishlaws.paymentserviceprovider.repository;

import rs.wepublishlaws.paymentserviceprovider.model.Merchant;

public interface MerchantRepository extends EntityRepository<Merchant> {
    boolean existsMerchantByMerchantId(String merchantId);
    Merchant findMerchantByApiKey(String apiKey);
}
