package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.DbBash;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = DbBash.REWARD_POINT)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserAccount customer;

    @Column(name = "points", nullable = false)
    private Integer points;
}
