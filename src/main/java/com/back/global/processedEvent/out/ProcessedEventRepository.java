package com.back.global.processedEvent.out;

import com.back.global.processedEvent.domain.ProcessedEvent;
import com.back.global.processedEvent.domain.type.ProcessedEventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, ProcessedEventId> {
}
