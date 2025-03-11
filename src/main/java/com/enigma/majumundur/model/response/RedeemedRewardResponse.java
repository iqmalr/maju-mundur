package com.enigma.majumundur.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RedeemedRewardResponse {
private String id;
private String customerId;
private String rewardId;
private LocalDateTime redeemedAt;
}
