package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketFacade;
import com.back.global.processedEvent.app.ProcessedEventFacade;
import com.back.global.serviceName.ServiceName;
import com.back.shared.cash.event.CashOrderPaymentFailedEvent;
import com.back.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.back.shared.market.event.MarketMemberCreatedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MarketEventListener {
    private final MarketFacade marketFacade;
    private final ProcessedEventFacade processedEventFacade;

    @KafkaListener(
            topics = "MemberJoinedEvent",
            groupId = "market-service"
    )
    @Transactional
    public void handleMemberJoined(MemberJoinedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        marketFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MARKET_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "MemberModifiedEvent",
            groupId = "market-service"
    )
    @Transactional
    public void handleMemberModified(MemberModifiedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        marketFacade.syncMember(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MARKET_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "MarketMemberCreatedEvent",
            groupId = "market-service"
    )
    @Transactional
    public void handleMarketMemberCreated(MarketMemberCreatedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        marketFacade.createCart(event.getMember());

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MARKET_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "CashOrderPaymentSucceededEvent",
            groupId = "market-service"
    )
    @Transactional
    public void handleCashOrderPaymentSucceeded(CashOrderPaymentSucceededEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        int orderId = event.getOrder().id();
        marketFacade.completeOrderPayment(orderId);

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MARKET_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }

    @KafkaListener(
            topics = "CashOrderPaymentFailedEvent",
            groupId = "market-service"
    )
    @Transactional
    public void handleCashOrderPaymentFailed(CashOrderPaymentFailedEvent event) {
        if (processedEventFacade.existsById(event.getEventId(), event.getProducerServiceName())) {
            return;
        }

        int orderId = event.getOrder().id();
        marketFacade.cancelOrderRequestPayment(orderId);

        processedEventFacade.createProcessedEvent(
                event.getEventId(),
                ServiceName.MARKET_SERVICE.value(),
                event.getProducerServiceName(),
                event.getEventType()
        );
    }
}