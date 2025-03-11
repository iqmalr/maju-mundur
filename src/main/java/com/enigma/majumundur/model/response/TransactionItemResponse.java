package com.enigma.majumundur.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItemResponse {
    private String productId;
    private Integer quantity;
    private BigDecimal subTotal;
}
