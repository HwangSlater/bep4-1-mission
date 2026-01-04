package com.back.boundedContext.member.in;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.global.processedEvent.app.ProcessedEventFacade;
import com.back.global.serviceName.ServiceName;
import com.back.shared.post.event.PostCommentCreatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberFacade memberFacade;
    private final ProcessedEventFacade processedEventFacade;

    @KafkaListener(
            topics = "PostCreatedEvent",
            groupId = "member-service"
    )
    @Transactional
    public void handlePostCreated(PostCreatedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        Member member = memberFacade
                .findById(event.getPostDto().authorId())
                .orElseThrow();

        member.increaseActivityScore(3);

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MEMBER_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "PostCommentCreatedEvent",
            groupId = "member-service"
    )
    @Transactional
    public void handlePostCommentCreated(PostCommentCreatedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        Member member = memberFacade
                .findById(event.getPostCommentDto().authorId())
                .orElseThrow();

        member.increaseActivityScore(1);

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MEMBER_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }
}
