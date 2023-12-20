package rs.wepublishlaws.paymentserviceprovider.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.wepublishlaws.paymentserviceprovider.model.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends EntityRepository<Subscription>{

    @Query(value = "SELECT jsonb_array_elements_text(s.payment_methods) FROM subscriptions s WHERE s.merchant_id = ?1", nativeQuery = true)
    List<String> findPaymentMethodsByMerchantId(String merchantId);

    Subscription findSubscriptionByMerchantId(String merchantId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO subscriptions (id, merchant_id, payment_methods) VALUES (nextval('subscriptions_id_seq'), ?, '[]')", nativeQuery = true)
    void insertSubscription(@Param("merchantId") String merchantId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE subscriptions SET payment_methods = CAST(:paymentMethods AS jsonb) WHERE merchant_id = :merchantId", nativeQuery = true)
    void updatePaymentMethods(@Param("merchantId") String merchantId, @Param("paymentMethods") String paymentMethodsAsJson);

}
