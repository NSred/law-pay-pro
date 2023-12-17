package rs.wepublishlaws.paymentserviceprovider.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.repository.MerchantRepository;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public MerchantServiceImpl(MerchantRepository repository) {
        this.repository = repository;
    }

    public boolean existsMerchantByMerchantId(String merchantId) {
        return repository.existsMerchantByMerchantId(merchantId);
    }

    public String generateApiKey(String merchantId, String merchantPassword) {
        String combinedString = merchantId + merchantPassword + System.currentTimeMillis();
        String apiKey = DigestUtils.sha256Hex(combinedString);
        save(merchantId, merchantPassword, apiKey);
        return apiKey;
    }

    @Override
    public Merchant findMerchantByApiKey(String apiKey) { return this.repository.findMerchantByApiKey(apiKey);}

    @Override
    public Merchant findMerchantByMerchantId(String merchantId) {
        return this.repository.findMerchantByMerchantId(merchantId);
    }

    private void save(String merchantId, String merchantPassword, String apiKey) {
        Merchant merchant = new Merchant();
        merchant.setMerchantId(merchantId);
        merchant.setMerchantPassword(passwordEncoder.encode(merchantPassword));
        merchant.setApiKey(apiKey);
        repository.save(merchant);
    }
}
