package com.back.boundedContext.payout.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.payout.dto.PayoutMemberDto;

import java.time.LocalDateTime;

public class PayoutMember extends ReplicaMember {
    public PayoutMember(int id, LocalDateTime createDate, LocalDateTime modifyDate
            , String username, String password, String nickname, int activityScore) {
        super(id, createDate, modifyDate, username, password, nickname, activityScore);
    }

    public PayoutMemberDto toDto() {
        return new PayoutMemberDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                getUsername(),
                getNickname(),
                getActivityScore()
        );
    }
}
