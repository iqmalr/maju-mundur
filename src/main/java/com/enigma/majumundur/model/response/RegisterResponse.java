package com.enigma.majumundur.model.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String id;
    private String username;
    private String role;
}
