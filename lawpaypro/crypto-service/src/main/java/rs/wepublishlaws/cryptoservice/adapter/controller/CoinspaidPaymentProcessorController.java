package rs.wepublishlaws.cryptoservice.adapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.cryptoservice.domain.service.PaymentService;

@RestController
@RequestMapping(path = "/crypto")
public class CoinspaidPaymentProcessorController {

    @Autowired
    private PaymentService paymentService;

//    @PostMapping("/notify")
//    public ResponseEntity<Void> notify(
//            @RequestBody final DepositCallbackResponseDto callbackResponseDto
//    ) throws Exception {
//        final PrizmaPaymentResponseDto prizmaResponseDto = coinspaidMapper.mapFromCallback(callbackResponseDto);
//        paymentService.sendNotification(prizmaResponseDto);
//        return ResponseEntity.ok().build();
//    }
}
