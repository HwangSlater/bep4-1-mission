package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.shared.cache.dto.CashMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {
    private final WalletRepository walletRepository;
    private final CashMemberRepository cashMemberRepository;

    @Transactional
    public Wallet createWallet(CashMemberDto holder) {
        CashMember cashMember = cashMemberRepository.getReferenceById(holder.id());
        Wallet wallet = new Wallet(cashMember);

        return walletRepository.save(wallet);
    }
}
