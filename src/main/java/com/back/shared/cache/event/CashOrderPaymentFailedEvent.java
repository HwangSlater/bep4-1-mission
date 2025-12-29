package com.back.shared.cache.event;

import com.back.shared.market.dto.OrderDto;

public record CashOrderPaymentFailedEvent(
        String resultCode,
        String msg,
        OrderDto order,
        long pgPaymentAmount,
        long shortfallAmount
) {}
