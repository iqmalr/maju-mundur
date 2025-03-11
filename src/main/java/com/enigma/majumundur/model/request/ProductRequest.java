package com.enigma.majumundur.model.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String merchantId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}
