package rs.wepublishlaws.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePayPalSubRequest {
    private Long id;
    private String subscriptionId;
}
