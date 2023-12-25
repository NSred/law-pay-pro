package rs.wepublishlaws.shared;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPayPalSubResponse {
    private Long id;
    private String subscriptionId;
    private String subscriptionStatus;
    private String planId;
}
