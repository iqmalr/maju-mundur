package com.enigma.majumundur.repository;

import com.enigma.majumundur.entity.RewardPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardPointRepository extends JpaRepository<RewardPoint, String> {
    Optional<RewardPoint> findByCustomerId(String customerId);
}

