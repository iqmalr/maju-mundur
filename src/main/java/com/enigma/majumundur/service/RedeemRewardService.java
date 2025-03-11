package com.enigma.majumundur.service;

import com.enigma.majumundur.model.request.RedeemRewardRequest;
import com.enigma.majumundur.model.response.RedeemedRewardResponse;
public interface RedeemRewardService {
    RedeemedRewardResponse redeemReward(RedeemRewardRequest request);
}
