package com.example.bank.DTO;

import java.time.LocalDateTime;

public class IdTimestampDTO {
    private Long id;
    private LocalDateTime timestamp;

    public IdTimestampDTO(Long id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "IdTimestampDTO{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
