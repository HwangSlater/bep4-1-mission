package com.back.global.processedEvent.app;

import com.back.global.processedEvent.domain.ProcessedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProcessedEventFacade {
    private final ProcessedEventSupport processedEventSupport;

    @Transactional(readOnly = true)
    public boolean existsById(String eventId, String consumerServiceName) {
        return processedEventSupport.existsById(eventId, consumerServiceName);
    }

    @Transactional
    public ProcessedEvent createProcessedEvent(String eventId, String consumerServiceName, String producerServiceName, String eventType) {
        return processedEventSupport.createProcessedEvent(eventId, consumerServiceName, producerServiceName, eventType);
    }
}
