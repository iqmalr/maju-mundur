package com.enigma.majumundur.repository;

import com.enigma.majumundur.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, String> {
}
