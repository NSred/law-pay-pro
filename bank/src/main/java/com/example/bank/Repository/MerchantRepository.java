package com.example.bank.Repository;

import com.example.bank.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    // Additional query methods if needed
    boolean existsMerchantByMerchantId(String merchantId);
    Merchant findMerchantByMerchantId(String merchantId);
}
