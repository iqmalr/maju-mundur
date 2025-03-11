package com.enigma.majumundur.model.response;

import com.enigma.majumundur.constant.TransactionStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private String customerId;
    private String merchantId;
    private BigDecimal totalPrice;
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private List<TransactionItemResponse> items;
}
