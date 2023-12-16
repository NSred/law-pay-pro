package rs.wepublishlaws.cryptoservice.adapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.cryptoservice.adapter.mapper.CoinspaidMapper;
import rs.wepublishlaws.cryptoservice.domain.model.callback.DepositCallbackResponseDto;
import rs.wepublishlaws.cryptoservice.domain.service.PaymentService;
import rs.wepublishlaws.shared.messages.PaymentResponse;

@RestController
@RequestMapping(path = "/crypto")
public class CoinspaidPaymentProcessorController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CoinspaidMapper coinspaidMapper;

    @PostMapping("/notify")
    public ResponseEntity<Void> notify(
            @RequestBody final DepositCallbackResponseDto callbackResponseDto
    ) throws Exception {
        final PaymentResponse response = coinspaidMapper.mapFromCallback(callbackResponseDto);
        paymentService.sendNotification(response);
        return ResponseEntity.ok().build();
    }
}
