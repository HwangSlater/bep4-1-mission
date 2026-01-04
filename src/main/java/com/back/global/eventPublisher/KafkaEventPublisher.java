package com.back.global.eventPublisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(BaseEvent event) {

            kafkaTemplate.send(
                    event.getEventType(),   // topic
                    event.getEventId(),     // key
                    event                // payload
            );
    }
}