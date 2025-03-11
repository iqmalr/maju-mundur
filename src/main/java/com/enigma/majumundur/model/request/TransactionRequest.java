package com.enigma.majumundur.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    @NotNull(message = "Customer ID cannot be null")
    private String customerId;

    @NotNull(message = "Merchant ID cannot be null")
    private String merchantId;

    @NotNull(message = "Total price cannot be null")
    private BigDecimal totalPrice;

    @NotNull(message = "Transaction items cannot be null")
    private List<TransactionItemRequest> items;
}
