package com.enigma.majumundur.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RewardResponse {
    private String id;
    private String name;
    private Integer pointsRequired;
}
