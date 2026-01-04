package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.global.processedEvent.app.ProcessedEventFacade;
import com.back.global.serviceName.ServiceName;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.payout.event.PayoutCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CashEventListener {
    private final CashFacade cashFacade;
    private final ProcessedEventFacade processedEventFacade;

    @KafkaListener(
            topics = "MemberJoinedEvent",
            groupId = "cash-service"
    )
    @Transactional
    public void handleMemberJoined(MemberJoinedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        cashFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.CASH_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "MemberModifiedEvent",
            groupId = "cash-service"
    )
    @Transactional
    public void handleMemberModified(MemberModifiedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        cashFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.CASH_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "CashMemberCreatedEvent",
            groupId = "cash-service"
    )
    @Transactional
    public void handleCashMemberCreated(CashMemberCreatedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        cashFacade.createWallet(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.CASH_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "MarketOrderPaymentRequestedEvent",
            groupId = "cash-service"
    )
    @Transactional
    public void handleMarketOrderPaymentRequested(MarketOrderPaymentRequestedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        cashFacade.completeOrderPayment(event.getOrder(), event.getPgPaymentAmount());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.CASH_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "PayoutCompletedEvent",
            groupId = "cash-service"
    )
    @Transactional
    public void handlePayoutCompleted(PayoutCompletedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        cashFacade.completePayout(event.getPayout());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.CASH_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }
}