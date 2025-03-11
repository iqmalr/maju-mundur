package com.enigma.majumundur.constant;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    TRANSACTION_STATUS_PENDING("Pending"),
    TRANSACTION_STATUS_REJECT("Reject"),
    TRANSACTION_STATUS_SUCCESS("Success");
    private final String description;
    TransactionStatus(String description){
        this.description=description;
    }
}
