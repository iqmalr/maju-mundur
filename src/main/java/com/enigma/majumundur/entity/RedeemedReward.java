package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.DbBash;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = DbBash.REDEEMED_REWARD)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedeemedReward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserAccount customer;

    @ManyToOne
    @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;

    @Column(name = "redeemed_at", nullable = false)
    private LocalDateTime redeemedAt;
}
