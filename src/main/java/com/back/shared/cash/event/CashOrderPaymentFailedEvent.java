package com.back.shared.cash.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.market.dto.OrderDto;
import com.back.standard.resultType.ResultType;
import lombok.Getter;

@Getter
public class CashOrderPaymentFailedEvent extends BaseEvent implements ResultType {
    String resultCode;
    String msg;
    OrderDto order;
    long pgPaymentAmount;
    long shortfallAmount;

    public CashOrderPaymentFailedEvent(String resultCode, String msg, OrderDto order, long pgPaymentAmount, long shortfallAmount) {
        super(ServiceName.CASH_SERVICE.value());

        this.resultCode = resultCode;
        this.msg = msg;
        this.order = order;
        this.pgPaymentAmount = pgPaymentAmount;
        this.shortfallAmount = shortfallAmount;
    }

    @Override
    public String resultCode() {
        return resultCode;
    }

    @Override
    public String msg() {
        return msg;
    }
}
