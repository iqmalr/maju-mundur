package com.enigma.majumundur.constant;

import lombok.Getter;

@Getter

public enum UserRole {
    ROLE_MERCHANT("Merchant"),
    ROLE_CUSTOMER("Customer");
    private final String description;
    UserRole(String description){
        this.description=description;
    }

    public static UserRole findByDescription(String description){
        for (UserRole role:values()){
            if (role.description.equalsIgnoreCase(description)){
                return role;
            }
        }
        return null;
    }
}
