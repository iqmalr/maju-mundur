package com.enigma.majumundur.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedeemRewardRequest {
    private String customerId;
    private String rewardId;
}
