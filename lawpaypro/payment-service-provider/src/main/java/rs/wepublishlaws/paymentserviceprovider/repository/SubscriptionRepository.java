package rs.wepublishlaws.paymentserviceprovider.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.wepublishlaws.paymentserviceprovider.model.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends EntityRepository<Subscription>{
    @Query(value = "SELECT jsonb_array_elements_text(s.payment_methods) FROM subscriptions s WHERE s.merchant_id = ?1", nativeQuery = true)
    List<String> findPaymentMethodsByMerchantId(String merchantId);
}
