package com.enigma.majumundur.service;

import com.enigma.majumundur.model.request.RewardRequest;
import com.enigma.majumundur.model.response.RewardResponse;

import java.util.List;

public interface RewardService {
    RewardResponse createReward(RewardRequest request);

    List<RewardResponse> getAllRewards();
}
