package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.TransactionStatus;
import com.enigma.majumundur.entity.RewardPoint;
import com.enigma.majumundur.entity.Transaction;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.model.request.TransactionRequest;
import com.enigma.majumundur.model.response.TransactionItemResponse;
import com.enigma.majumundur.model.response.TransactionResponse;
import com.enigma.majumundur.repository.ProductRepository;
import com.enigma.majumundur.repository.RewardPointRepository;
import com.enigma.majumundur.repository.TransactionRepository;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.enigma.majumundur.util.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserAccountRepository userAccountRepository;
    private final RewardPointRepository rewardPointRepository;

    @Transactional
    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        UserAccount customer = userAccountRepository.findById(transactionRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer Not Found"));
        UserAccount merchant = userAccountRepository.findById(transactionRequest.getMerchantId())
                .orElseThrow(() -> new NotFoundException("Merchant Not Found"));

        Transaction transaction = Transaction.builder()
                .customer(customer)
                .merchant(merchant)
                .totalPrice(transactionRequest.getTotalPrice())
                .status(TransactionStatus.TRANSACTION_STATUS_PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        addRewardPoints(customer, transactionRequest.getTotalPrice());
        return TransactionResponse.builder()
                .id(savedTransaction.getId())
                .customerId(savedTransaction.getCustomer().getId())
                .merchantId(savedTransaction.getMerchant().getId())
                .totalPrice(savedTransaction.getTotalPrice())
                .status(savedTransaction.getStatus())
                .createdAt(savedTransaction.getCreatedAt())
                .items(List.of())
                .build();

    }

    private void addRewardPoints(UserAccount customer, BigDecimal totalPrice) {
        int earnedPoints = totalPrice.divide(new BigDecimal("10000.00")).intValue();


        RewardPoint rewardPoint = rewardPointRepository.findByCustomerId(customer.getId())
                .orElse(RewardPoint.builder()
                        .customer(customer)
                        .points(0)
                        .build());

        rewardPoint.setPoints(rewardPoint.getPoints() + earnedPoints);
        rewardPointRepository.save(rewardPoint);
    }

    @Override
    public TransactionResponse getTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        List<TransactionItemResponse> items = transaction.getTransactionItems().stream()
                .map(item -> new TransactionItemResponse(
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getSubTotal()
                )).collect(Collectors.toList());

        return TransactionResponse.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .merchantId(transaction.getMerchant().getId())
                .totalPrice(transaction.getTotalPrice())
                .status(transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .items(items)
                .build();
    }
}
