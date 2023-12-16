package rs.wepublishlaws.paymentserviceprovider.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.wepublishlaws.paymentserviceprovider.model.converters.JsonCollectionConverter;

import java.io.Serializable;
import java.util.List;

@Entity(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String merchantId;
    @Convert(converter = JsonCollectionConverter.class)
    @Column(columnDefinition = "jsonb", name = "payment_methods")
    private List<String> paymentMethods;
}
