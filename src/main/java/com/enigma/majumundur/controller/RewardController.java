package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiBash;
import com.enigma.majumundur.model.request.RedeemRewardRequest;
import com.enigma.majumundur.model.request.RewardRequest;
import com.enigma.majumundur.model.response.RedeemedRewardResponse;
import com.enigma.majumundur.model.response.RewardResponse;
import com.enigma.majumundur.service.RedeemRewardService;
import com.enigma.majumundur.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiBash.REWARD)
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;
    private final RedeemRewardService redeemRewardService;

    @PostMapping
    public ResponseEntity<RewardResponse> createReward(@RequestBody RewardRequest rewardRequest){
        return ResponseEntity.ok(rewardService.createReward(rewardRequest));
    }

    @GetMapping
    public ResponseEntity<List<RewardResponse>> getAllRewards(){
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    @PostMapping(ApiBash.REDEEM)
    public ResponseEntity<RedeemedRewardResponse> redeemReward(@RequestBody RedeemRewardRequest redeemRewardRequest){
        return ResponseEntity.ok(redeemRewardService.redeemReward(redeemRewardRequest));
    }
}
