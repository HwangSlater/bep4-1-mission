package com.back.global.serviceName;

public enum ServiceName {
    MEMBER_SERVICE,
    POST_SERVICE,
    MARKET_SERVICE,
    PAYOUT_SERVICE,
    CASH_SERVICE;

    public String value() {
        return name()
                .toLowerCase()
                .replace("_", "-");
    }
}
