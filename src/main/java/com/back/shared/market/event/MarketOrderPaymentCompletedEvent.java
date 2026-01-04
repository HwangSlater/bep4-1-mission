package com.back.shared.market.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.market.dto.OrderDto;
import lombok.Getter;

@Getter
public class MarketOrderPaymentCompletedEvent extends BaseEvent {
    OrderDto order;

    public MarketOrderPaymentCompletedEvent(OrderDto order) {
        super(ServiceName.MARKET_SERVICE.value());

        this.order = order;
    }
}