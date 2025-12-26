package com.back.shared.cache.event;

import com.back.shared.cache.dto.CashMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CashMemberCreatedEvent {
    private CashMemberDto cashMemberDto;
}
