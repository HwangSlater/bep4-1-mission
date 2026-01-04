package com.back.global.processedEvent.domain.type;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProcessedEventId implements Serializable {
    private String eventId;
    private String consumerServiceName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessedEventId that)) return false;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(consumerServiceName, that.consumerServiceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, consumerServiceName);
    }
}
