package com.back.shared.market.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.market.dto.OrderDto;
import lombok.Getter;

@Getter
public class MarketOrderPaymentRequestedEvent extends BaseEvent {
    OrderDto order;
    long pgPaymentAmount;

    public MarketOrderPaymentRequestedEvent(OrderDto order, long pgPaymentAmount) {
        super(ServiceName.MARKET_SERVICE.value());

        this.order = order;
        this.pgPaymentAmount = pgPaymentAmount;
    }
}
