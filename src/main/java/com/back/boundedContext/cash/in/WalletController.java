package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.shared.cash.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final CashFacade cashFacade;

    @GetMapping("/by-holder/{holderId}")
    @Transactional(readOnly = true)
    public WalletDto getItemByHolder(
            @PathVariable int holderId
    ) {
        return cashFacade
                .findWalletByHolderId(holderId)
                .map(WalletDto::new)
                .get();
    }
}
