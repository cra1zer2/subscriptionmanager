package com.tsuprenko.subscriptionmanager;

import com.tsuprenko.subscriptionmanager.domain.Subscription;
import com.tsuprenko.subscriptionmanager.repository.SubscriptionRepository;
import com.tsuprenko.subscriptionmanager.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class SubscriptionIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionRepository repository;

    @Test
    void shouldSaveAndRetrieveSubscription() {
        // GIVEN
        Subscription sub = Subscription.builder()
                .providerName("Integration Test Sub")
                .amount(new BigDecimal("100.00"))
                .currency("PLN")
                .build();

        // WHEN
        var savedDto = subscriptionService.createSubscription(sub);

        // THEN
        assertNotNull(savedDto.id());

        var foundInDb = repository.findById(savedDto.id()).orElseThrow();
        assertEquals("Integration Test Sub", foundInDb.getProviderName());
        assertEquals(0, new BigDecimal("100.00").compareTo(foundInDb.getAmount()));
    }
}