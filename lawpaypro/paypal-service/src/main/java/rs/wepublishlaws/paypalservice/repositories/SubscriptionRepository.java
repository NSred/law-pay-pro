package rs.wepublishlaws.paypalservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.wepublishlaws.paypalservice.models.Subscription;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUserIdAndOfferId(Long userId, UUID offerId);
    default Subscription findOne(Long id) {
        return findById(id).orElse(null);
    }
}
