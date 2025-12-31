package com.back.shared.payout.dto;

import com.back.standard.modelType.CanGetModelTypeCode;

import java.time.LocalDateTime;

public record PayoutDto (
    int id,
    LocalDateTime createDate,
    LocalDateTime modifyDate,
    int payeeId,
    String payeeName,
    LocalDateTime payoutDate,
    long amount,
    boolean isPayeeSystem
) implements CanGetModelTypeCode {
    @Override
    public String getModelTypeCode() {
        return "Payout";
    }
}