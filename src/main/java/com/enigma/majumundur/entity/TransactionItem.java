package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.DbBash;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = DbBash.TRANSACTION_ITEM)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;
}
