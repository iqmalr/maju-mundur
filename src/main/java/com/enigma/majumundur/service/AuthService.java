package com.enigma.majumundur.service;

import com.enigma.majumundur.model.request.AuthRequest;
import com.enigma.majumundur.model.response.AuthResponse;
import com.enigma.majumundur.model.response.LogoutResponse;
import com.enigma.majumundur.model.response.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    RegisterResponse createMerchant(AuthRequest authRequest);

    RegisterResponse createCustomer(AuthRequest authRequest);

    AuthResponse login(AuthRequest authRequest);

    LogoutResponse logout(HttpServletRequest httpServletRequest);
}
