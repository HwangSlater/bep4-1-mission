package com.back.boundedContext.cache.out;

import com.back.boundedContext.cache.domain.CashMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMemberRepository extends JpaRepository<CashMember, Integer> {
}
