package com.back.boundedContext.cache.app;

import com.back.boundedContext.cache.domain.CashMember;
import com.back.boundedContext.cache.domain.Wallet;
import com.back.boundedContext.cache.out.CashMemberRepository;
import com.back.boundedContext.cache.out.WalletRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

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

    @Transactional
    public Wallet createWallet(CashMember cashMember) {
        Wallet wallet = new Wallet(cashMember);

        return walletRepository.save(wallet);
    }
}
