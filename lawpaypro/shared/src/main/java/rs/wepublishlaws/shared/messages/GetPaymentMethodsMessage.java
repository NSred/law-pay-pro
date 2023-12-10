package rs.wepublishlaws.shared.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentMethodsMessage {
    private Collection<String> paymentMethods;
    private boolean isError;
    private String reason;
}
