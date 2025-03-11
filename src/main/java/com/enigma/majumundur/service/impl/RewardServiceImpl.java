package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.entity.Reward;
import com.enigma.majumundur.model.request.RewardRequest;
import com.enigma.majumundur.model.response.RewardResponse;
import com.enigma.majumundur.repository.RewardRepository;
import com.enigma.majumundur.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {
    private final RewardRepository rewardRepository;

    @Override
    public RewardResponse createReward(RewardRequest rewardRequest) {
        Reward reward = Reward.builder()
                .name(rewardRequest.getName())
                .pointsRequired(rewardRequest.getPointsRequired())
                .build();
        Reward savedReward = rewardRepository.save(reward);
        return RewardResponse.builder()
                .id(savedReward.getId())
                .name(savedReward.getName())
                .pointsRequired(savedReward.getPointsRequired()).build();
    }
    @Override
    public List<RewardResponse> getAllRewards() {
        return rewardRepository.findAll().stream()
                .map(reward -> RewardResponse.builder()
                        .id(reward.getId())
                        .name(reward.getName())
                        .pointsRequired(reward.getPointsRequired())
                        .build())
                .collect(Collectors.toList());
    }
}
