package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.entity.RedeemedReward;
import com.enigma.majumundur.entity.Reward;
import com.enigma.majumundur.entity.RewardPoint;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.model.request.RedeemRewardRequest;
import com.enigma.majumundur.model.response.RedeemedRewardResponse;
import com.enigma.majumundur.repository.RedeemedRewardRepository;
import com.enigma.majumundur.repository.RewardPointRepository;
import com.enigma.majumundur.repository.RewardRepository;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.RedeemRewardService;
import com.enigma.majumundur.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RedeemRewardServiceImpl implements RedeemRewardService {
    private final RewardRepository rewardRepository;
    private final RewardPointRepository rewardPointRepository;
    private final RedeemedRewardRepository redeemedRewardRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional
    @Override
    public RedeemedRewardResponse redeemReward(RedeemRewardRequest request) {
        UserAccount customer = userAccountRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        Reward reward = rewardRepository.findById(request.getRewardId())
                .orElseThrow(() -> new NotFoundException("Reward not found"));

        RewardPoint rewardPoint = rewardPointRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new NotFoundException("No reward points found for this customer"));

        if (rewardPoint.getPoints() < reward.getPointsRequired()) {
            throw new IllegalArgumentException("Not enough points to redeem this reward");
        }

        rewardPoint.setPoints(rewardPoint.getPoints() - reward.getPointsRequired());
        rewardPointRepository.save(rewardPoint);

        RedeemedReward redeemedReward = RedeemedReward.builder()
                .customer(customer)
                .reward(reward)
                .redeemedAt(LocalDateTime.now())
                .build();
        RedeemedReward savedReward = redeemedRewardRepository.save(redeemedReward);

        return RedeemedRewardResponse.builder()
                .id(savedReward.getId())
                .customerId(savedReward.getCustomer().getId())
                .rewardId(savedReward.getReward().getId())
                .redeemedAt(savedReward.getRedeemedAt())
                .build();
    }
}
