package com.tsuprenko.subscriptionmanager.dto;

import java.math.BigDecimal;

public record SubscriptionResponse(
        Long id,
        String providerName,
        BigDecimal amount,
        String currency
) {}
