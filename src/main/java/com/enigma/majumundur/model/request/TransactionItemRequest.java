package com.enigma.majumundur.model.request;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItemRequest {
    @NotNull(message = "Product ID cannot be null")
    private String productId;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Subtotal cannot be null")
    private BigDecimal subTotal;
}
