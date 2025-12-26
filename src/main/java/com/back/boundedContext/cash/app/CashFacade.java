package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CashFacade {
    private final CashSyncWalletUseCase cashSyncWalletUseCase;
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashSupport cashSupport;

    @Transactional
    public CashMember syncMember(MemberDto member) {
        return cashSyncWalletUseCase.syncMember(member);
    }

    @Transactional
    public Wallet createWallet(CashMember cashMember) {
        return cashCreateWalletUseCase.createWallet(cashMember);
    }

    @Transactional(readOnly = true)
    public Optional<CashMember> findMemberByUsername(String username) {
        return cashSupport.findMemberByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findWalletByHolder(CashMember holder) {
        return cashSupport.findWalletByHolder(holder);
    }
}
