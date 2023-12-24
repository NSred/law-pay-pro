package rs.wepublishlaws.paypalservice.services.subscriptions;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.wepublishlaws.paypalservice.models.Subscription;
import rs.wepublishlaws.paypalservice.repositories.SubscriptionRepository;
import rs.wepublishlaws.shared.GetPayPalSubRequest;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;
    private final RestTemplate restTemplate;
    @Value("${paypal.api.url}")
    private String paypalApiUrl;
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Override
    public Subscription getByUserAndOffer(GetPayPalSubRequest request) {
        return subscriptionRepository.findByUserIdAndOfferId(request.getUserId(), request.getOfferId());
    }

    @Override
    public Subscription findOne(Long id) {
        return subscriptionRepository.findOne(id);
    }

    @Override
    public void updateSub(Subscription sub, String subscriptionId) {
        sub.setSubscriptionId(subscriptionId);
        sub.setSubscriptionStatus("ACTIVE");
        subscriptionRepository.save(sub);
    }

    @Override
    public void cancelSub(Subscription sub, String subscriptionId) {
        sub.setSubscriptionId(null);
        sub.setSubscriptionStatus(null);
        subscriptionRepository.save(sub);

        //cancel on pay pal
        String url = paypalApiUrl + "/v1/billing/subscriptions/" + subscriptionId + "/cancel";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>("{ \"reason\": \"Not satisfied with the service\" }", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getStatusCode());

    }
}
