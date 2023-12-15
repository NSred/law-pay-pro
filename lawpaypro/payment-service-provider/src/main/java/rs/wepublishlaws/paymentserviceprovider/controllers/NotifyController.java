package rs.wepublishlaws.paymentserviceprovider.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rs.wepublishlaws.paymentserviceprovider.service.SseService;
import rs.wepublishlaws.shared.messages.requests.PaymentStatusNotify;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
public class NotifyController {
    private final SseService sseService;
    @PostMapping("/redirect")
    public void notify(@RequestBody PaymentStatusNotify request){
        sseService.sendToAll(request);
    }

    @GetMapping("/stream")
    public SseEmitter stream(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Content-Type", "text/event-stream");
        return sseService.createEmitter();
    }
}
