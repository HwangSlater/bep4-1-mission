package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.global.processedEvent.app.ProcessedEventFacade;
import com.back.global.serviceName.ServiceName;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostEventListener {
    private final PostFacade postFacade;
    private final ProcessedEventFacade  processedEventFacade;

    @KafkaListener(
            topics = "MemberJoinedEvent",
            groupId = "post-service"
    )
    @Transactional
    public void handleMemberJoined(MemberJoinedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        postFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.POST_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "MemberModifiedEvent",
            groupId = "post-service"
    )
    @Transactional
    public void handleMemberModified(MemberModifiedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        postFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                        event.getEventId(),
                        ServiceName.POST_SERVICE.value(),
                        event.getProducerServiceName(),
                        event.getEventType()
        );
    }
}