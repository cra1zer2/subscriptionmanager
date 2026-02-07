package com.tsuprenko.subscriptionmanager.service;

import com.tsuprenko.subscriptionmanager.client.NbpClient;
import com.tsuprenko.subscriptionmanager.domain.Subscription;
import com.tsuprenko.subscriptionmanager.repository.SubscriptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository repository;

    @Mock
    private NbpClient nbpClient;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    @DisplayName("Should calculate total cost correctly for a single USD subscription")
    void shouldCalculateTotalMonthlyCostCorrectly() {
        Subscription sub = Subscription.builder()
                .providerName("Netflix")
                .amount(new BigDecimal("10.00"))
                .currency("USD")
                .build();

        when(repository.findAll()).thenReturn(List.of(sub));
        when(nbpClient.getExchangeRate("USD")).thenReturn(new BigDecimal("4.00"));

        BigDecimal total = subscriptionService.calculateTotalMonthlyCost();

        assertTrue(new BigDecimal("40.00").compareTo(total) == 0);
    }

    @Test
    @DisplayName("Should use 1.0 rate if NBP client fails")
    void shouldReturnOriginalAmountWhenNbpFails() {
        Subscription sub = Subscription.builder()
                .providerName("Netflix")
                .amount(new BigDecimal("10.00"))
                .currency("USD")
                .build();

        when(repository.findAll()).thenReturn(List.of(sub));
        when(nbpClient.getExchangeRate(anyString())).thenReturn(BigDecimal.ONE);

        BigDecimal total = subscriptionService.calculateTotalMonthlyCost();

        assertTrue(new BigDecimal("10.00").compareTo(total) == 0);
    }

    @Test
    @DisplayName("Should return zero when there are no subscriptions in database")
    void shouldReturnZeroWhenNoSubscriptionsPresent() {
        when(repository.findAll()).thenReturn(List.of());

        BigDecimal total = subscriptionService.calculateTotalMonthlyCost();

        assertTrue(new BigDecimal("0.00").compareTo(total) == 0);
    }
}