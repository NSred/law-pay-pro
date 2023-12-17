package rs.wepublishlaws.paymentserviceprovider.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "payment_method_services")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String queueName;
    private String imageUrl;
}
