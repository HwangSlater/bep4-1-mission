package com.back.shared.cash.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.market.dto.OrderDto;
import lombok.Getter;

@Getter
public class CashOrderPaymentSucceededEvent extends BaseEvent {
    OrderDto order;
    long pgPaymentAmount;

    public CashOrderPaymentSucceededEvent(OrderDto order, long pgPaymentAmount) {
        super(ServiceName.CASH_SERVICE.value());

        this.order = order;
        this.pgPaymentAmount = pgPaymentAmount;
    }
}
