package com.back.shared.cache.event;

import com.back.shared.market.dto.OrderDto;

public record CashOrderPaymentSucceededEvent(OrderDto order, long pgPaymentAmount) {
}
