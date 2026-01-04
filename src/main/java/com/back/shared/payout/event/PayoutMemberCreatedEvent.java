package com.back.shared.payout.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.Getter;

@Getter
public class PayoutMemberCreatedEvent extends BaseEvent {
    PayoutMemberDto member;

    public PayoutMemberCreatedEvent(PayoutMemberDto member) {
        super(ServiceName.PAYOUT_SERVICE.value());

        this.member = member;
    }

}