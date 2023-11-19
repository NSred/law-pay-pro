package com.example.bank.Service;

import com.example.bank.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository repository;

    //mozda proveriti i sifru i id u buducnosti
    public boolean existsByMerchantId(String merchantId){
        return repository.existsMerchantByMerchantId(merchantId);
    }
}
