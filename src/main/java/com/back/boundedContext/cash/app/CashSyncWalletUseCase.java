package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashSyncWalletUseCase {
    private final CashMemberRepository cashMemberRepository;

    @Transactional
    public CashMember syncMember(MemberDto member) {
        CashMember cashMember = new CashMember(
                member.id(),
                member.createDate(),
                member.modifyDate(),
                member.username(),
                "",
                member.nickname(),
                member.activityScore()
        );

        return cashMemberRepository.save(cashMember);
    }
}
