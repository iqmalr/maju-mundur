package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiBash;
import com.enigma.majumundur.model.request.TransactionRequest;
import com.enigma.majumundur.model.response.TransactionResponse;
import com.enigma.majumundur.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiBash.TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable String id) {
        TransactionResponse transactionResponse = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transactionResponse);
    }
}
