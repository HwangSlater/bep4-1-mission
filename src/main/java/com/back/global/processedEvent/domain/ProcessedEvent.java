package com.back.global.processedEvent.domain;

import com.back.global.processedEvent.domain.type.ProcessedEventId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * 외부/비동기 이벤트가 특정 서비스에서 이미 처리되었는지를 기록하는
 * 중복 처리 방지(Deduplication) 레지스트리 엔티티.
 *
 * <br>
 * - 동일 eventId라도 여러 서비스에서 각각 처리될 수 있음
 * - 따라서 복합키 (eventId, serviceName)를 식별자로 사용
 *   → 서비스별 독립적인 처리 이력 보장
 */
@Entity
@Table(name = "PROCESSED_EVENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ProcessedEvent {

    @EmbeddedId
    private ProcessedEventId processedEventId;

    @Column(nullable = false, length = 100, comment = "이벤트 발생 서비스")
    private String producerServiceName;

    @Column(nullable = false, length = 150)
    private String eventType;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime processedAt;

    public static ProcessedEvent of(String eventId, String serviceName,
                                    String producerServiceName, String eventType) {
        return ProcessedEvent.builder()
                .processedEventId(new ProcessedEventId(eventId, serviceName))
                .producerServiceName(producerServiceName)
                .eventType(eventType)
                .processedAt(LocalDateTime.now())
                .build();
    }
}