package com.back.shared.market.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.market.dto.MarketMemberDto;
import lombok.Getter;

@Getter
public class MarketMemberCreatedEvent extends BaseEvent {
    MarketMemberDto member;

    public MarketMemberCreatedEvent(MarketMemberDto member) {
        super(ServiceName.MARKET_SERVICE.value());

        this.member = member;
    }
}
