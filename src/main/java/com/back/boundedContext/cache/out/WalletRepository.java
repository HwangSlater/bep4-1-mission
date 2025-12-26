package com.back.boundedContext.cache.out;

import com.back.boundedContext.cache.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
