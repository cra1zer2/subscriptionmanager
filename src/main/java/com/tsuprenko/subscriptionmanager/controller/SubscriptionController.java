package com.tsuprenko.subscriptionmanager.controller;

import com.tsuprenko.subscriptionmanager.domain.Subscription;
import com.tsuprenko.subscriptionmanager.dto.SubscriptionResponse;
import com.tsuprenko.subscriptionmanager.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionResponse> getAll() {
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse create(@Valid @RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @GetMapping("/total")
    public BigDecimal getTotalCost() {
        return subscriptionService.calculateTotalMonthlyCost();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }
}