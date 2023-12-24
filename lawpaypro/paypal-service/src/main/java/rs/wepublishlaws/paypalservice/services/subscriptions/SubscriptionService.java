package rs.wepublishlaws.paypalservice.services.subscriptions;

import rs.wepublishlaws.paypalservice.models.Subscription;
import rs.wepublishlaws.shared.GetPayPalSubRequest;
import rs.wepublishlaws.shared.UpdatePayPalSubRequest;

public interface SubscriptionService {
    Subscription getByUserAndOffer(GetPayPalSubRequest request);
    Subscription findOne(Long id);
    void updateSub(Subscription sub, String subscriptionId);
    void cancelSub(Subscription sub, String subscriptionId);
}
