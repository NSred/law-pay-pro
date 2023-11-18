package rs.wepublishlaws.paymentserviceprovider.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "merchants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchants")
public class Merchant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String merchantId;
    private String merchantPassword;
    private String apiKey;

}
