package com.enigma.majumundur.service;

import com.enigma.majumundur.model.request.TransactionRequest;
import com.enigma.majumundur.model.response.TransactionResponse;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService {
    @Transactional
    TransactionResponse createTransaction(TransactionRequest transactionRequest);

    TransactionResponse getTransactionById(String id);
}
