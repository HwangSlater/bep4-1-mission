package com.back.shared.member.event;

import com.back.global.eventPublisher.BaseEvent;
import com.back.global.serviceName.ServiceName;
import com.back.shared.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class MemberModifiedEvent extends BaseEvent {
    MemberDto member;

    public MemberModifiedEvent(MemberDto member) {
        super(ServiceName.MEMBER_SERVICE.value());

        this.member = member;
    }
}
