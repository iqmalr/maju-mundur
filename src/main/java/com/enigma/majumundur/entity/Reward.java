package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.DbBash;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = DbBash.REWARD)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "points_required", nullable = false)
    private Integer pointsRequired;
}
