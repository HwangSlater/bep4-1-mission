package com.back.shared.cash.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.cash.dto.CashMemberDto;
import lombok.Getter;

@Getter
public class CashMemberCreatedEvent extends BaseEvent {
    private final CashMemberDto member;

    public CashMemberCreatedEvent(CashMemberDto member) {
        super(ServiceName.CASH_SERVICE.value());
        
        this.member = member;
    }
}