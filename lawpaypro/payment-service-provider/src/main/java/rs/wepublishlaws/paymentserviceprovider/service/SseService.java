package rs.wepublishlaws.paymentserviceprovider.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rs.wepublishlaws.shared.messages.requests.PaymentStatusNotify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SseService {
    private final List<SseEmitter> emitters = new ArrayList<>();

    public SseEmitter createEmitter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
        emitter.onError(e -> this.emitters.remove(emitter)); // Handle errors
        System.out.println("Creating emitter...");
        return emitter;
    }

    public void sendToAll(PaymentStatusNotify obj) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().data(obj));
                System.out.println("Sending event...");
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });
        this.emitters.removeAll(deadEmitters);
    }
}
