package com.back.global.processedEvent.app;

import com.back.global.processedEvent.domain.ProcessedEvent;
import com.back.global.processedEvent.domain.type.ProcessedEventId;
import com.back.global.processedEvent.out.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessedEventSupport {
    private final ProcessedEventRepository processedEventRepository;

    public boolean existsById(String eventId, String consumerServiceName) {
        return processedEventRepository.existsById(new ProcessedEventId(eventId, consumerServiceName));
    }

    public ProcessedEvent createProcessedEvent(String eventId, String consumerServiceName, String producerServiceName, String eventType) {
        return processedEventRepository.save(
                ProcessedEvent.of(eventId, consumerServiceName, producerServiceName, eventType)
        );
    }
}
