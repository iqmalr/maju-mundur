package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiBash;
import com.enigma.majumundur.model.request.AuthRequest;
import com.enigma.majumundur.model.response.AuthResponse;
import com.enigma.majumundur.model.response.CommonResponse;
import com.enigma.majumundur.model.response.LogoutResponse;
import com.enigma.majumundur.model.response.RegisterResponse;
import com.enigma.majumundur.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiBash.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiBash.REGISTER_MERCHANT)
    public ResponseEntity<CommonResponse<?>> registerMerchant(@RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.createMerchant(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ApiBash.SUCCESS_REGISTER)
                .data(registerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiBash.REGISTER_CUSTOMER)
    public ResponseEntity<CommonResponse<?>> registerCustomer(@RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.createCustomer(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ApiBash.SUCCESS_REGISTER)
                .data(registerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiBash.LOGIN)
    public ResponseEntity<CommonResponse<AuthResponse>> loginMerchant(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);
        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ApiBash.SUCCESS_LOGIN)
                .data(authResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiBash.LOGOUT)
    public ResponseEntity<CommonResponse<LogoutResponse>> logout(HttpServletRequest httpServletRequest) {
        LogoutResponse logoutResponse = authService.logout(httpServletRequest);

        CommonResponse<LogoutResponse> response = CommonResponse.<LogoutResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ApiBash.SUCCESS_LOGOUT)
                .data(logoutResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
