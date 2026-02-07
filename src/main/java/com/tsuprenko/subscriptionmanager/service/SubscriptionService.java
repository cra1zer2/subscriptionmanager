package com.tsuprenko.subscriptionmanager.service;

import com.tsuprenko.subscriptionmanager.client.NbpClient;
import com.tsuprenko.subscriptionmanager.domain.Subscription;
import com.tsuprenko.subscriptionmanager.dto.SubscriptionResponse;
import com.tsuprenko.subscriptionmanager.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(
                sub -> new SubscriptionResponse(
                        sub.getId(),
                        sub.getProviderName(),
                        sub.getAmount(),
                        sub.getCurrency()
                )
        ).toList();
    }

    private final NbpClient nbpClient;

    public SubscriptionResponse createSubscription(Subscription subscription){
        Subscription saved =  subscriptionRepository.save(subscription);
        return new SubscriptionResponse(
                saved.getId(),
                saved.getProviderName(),
                saved.getAmount(),
                saved.getCurrency()
        );
    }

    public BigDecimal calculateTotalMonthlyCost(){
        BigDecimal total = subscriptionRepository.findAll().stream()
                .map(sub -> {
                    BigDecimal rate = nbpClient.getExchangeRate(sub.getCurrency());
                    return sub.getAmount().multiply(rate);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.setScale(2, RoundingMode.HALF_UP);
    }

}
