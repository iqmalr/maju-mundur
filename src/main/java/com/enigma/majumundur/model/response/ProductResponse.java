package com.enigma.majumundur.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String merchantId;
    private String merchantName;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}
