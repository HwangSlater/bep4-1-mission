package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import com.back.boundedContext.payout.out.PayoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutCreatePayoutUseCase {
    private final PayoutMemberRepository payoutMemberRepository;
    private final PayoutRepository payoutRepository;

    public Payout createPayout(int payeeId) {
        PayoutMember _payee = payoutMemberRepository.getReferenceById(payeeId);

        Payout payout = payoutRepository.save(
                new Payout(
                        _payee
                )
        );

        return payout;
    }
}