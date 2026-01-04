package com.back.global.eventPublisher;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class BaseEvent {
    private final String eventId;
    private final String eventType; // ex: CashMemberCreatedEvent
    private final String producerServiceName; // ex: cash-service
    private final LocalDateTime occurredAt;

    protected BaseEvent(String producerServiceName) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = this.getClass().getSimpleName();
        this.producerServiceName = producerServiceName;
        this.occurredAt = LocalDateTime.now();
    }
}