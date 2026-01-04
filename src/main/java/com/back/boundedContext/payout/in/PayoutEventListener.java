package com.back.boundedContext.payout.in;

import com.back.boundedContext.payout.app.PayoutFacade;
import com.back.global.processedEvent.app.ProcessedEventFacade;
import com.back.global.serviceName.ServiceName;
import com.back.shared.market.event.MarketOrderPaymentCompletedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.payout.event.PayoutCompletedEvent;
import com.back.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PayoutEventListener {
    private final PayoutFacade payoutFacade;
    private final ProcessedEventFacade processedEventFacade;

    @KafkaListener(topics = "MemberJoinedEvent", groupId = "payout-service")
    @Transactional
    public void handleMemberJoined(MemberJoinedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        payoutFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.PAYOUT_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(topics = "MemberModifiedEvent", groupId = "payout-service")
    @Transactional
    public void handleMemberModified(MemberModifiedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        payoutFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.PAYOUT_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(topics = "PayoutMemberCreatedEvent", groupId = "payout-service")
    @Transactional
    public void handlePayoutMemberCreated(PayoutMemberCreatedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        payoutFacade.createPayout(event.getMember().id());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.PAYOUT_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(topics = "MarketOrderPaymentCompletedEvent", groupId = "payout-service")
    @Transactional
    public void handleMarketOrderPaymentCompleted(MarketOrderPaymentCompletedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        payoutFacade.addPayoutCandidateItems(event.getOrder());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.PAYOUT_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(topics = "PayoutCompletedEvent", groupId = "payout-service")
    @Transactional
    public void handlePayoutCompleted(PayoutCompletedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        payoutFacade.createPayout(event.getPayout().payeeId());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.PAYOUT_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }
}
