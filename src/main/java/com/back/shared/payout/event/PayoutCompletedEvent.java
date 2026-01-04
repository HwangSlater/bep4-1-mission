package com.back.shared.payout.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.payout.dto.PayoutDto;
import lombok.Getter;

@Getter
public class PayoutCompletedEvent extends BaseEvent {
    PayoutDto payout;

    public PayoutCompletedEvent(PayoutDto payout) {
        super(ServiceName.PAYOUT_SERVICE.value());

        this.payout = payout;
    }
}
