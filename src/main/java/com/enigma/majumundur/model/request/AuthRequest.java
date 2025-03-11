package com.enigma.majumundur.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank(message = "user is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
}
