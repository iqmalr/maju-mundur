package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.Constant;
import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.model.request.AuthRequest;
import com.enigma.majumundur.model.response.AuthResponse;
import com.enigma.majumundur.model.response.LogoutResponse;
import com.enigma.majumundur.model.response.RegisterResponse;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.security.JwtAuthenticationFilter;
import com.enigma.majumundur.security.JwtTokenProvider;
import com.enigma.majumundur.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    public final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenBlacklistService redisTokenBlacklistService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse createMerchant(AuthRequest authRequest) {
        try {
            UserRole userRole = UserRole.ROLE_MERCHANT;
            UserAccount userAccount = UserAccount.builder()
                    .username(authRequest.getUsername())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(userRole)
                    .build();
            userAccountRepository.saveAndFlush(userAccount);
            return toRegisterResponse(userAccount);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constant.USERNAME_DUPLICATE);
        }
    }

    @Override
    public RegisterResponse createCustomer(AuthRequest authRequest) {
        try {
            UserRole userRole = UserRole.ROLE_CUSTOMER;
            UserAccount userAccount = UserAccount.builder()
                    .username(authRequest.getUsername())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(userRole)
                    .build();
            userAccountRepository.saveAndFlush(userAccount);
            return toRegisterResponse(userAccount);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constant.USERNAME_DUPLICATE);
        }
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getAuthorities().toString());
        return AuthResponse.builder()
                .accessToken(token)
                .role(user.getAuthorities().toString())
                .build();
    }

    private static RegisterResponse toRegisterResponse(UserAccount userAccount) {
        return RegisterResponse.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .role(userAccount.getRole().getDescription())
                .build();
    }

    @Override
    public LogoutResponse logout(HttpServletRequest httpServletRequest) {
        String token = jwtAuthenticationFilter.extractTokenFromRequest(httpServletRequest);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
        Long expirationTime = jwtTokenProvider.getExpirationTime(token);
        redisTokenBlacklistService.blackListToken(token, expirationTime);
        return LogoutResponse.builder()
                .message("Token successfully blacklisted")
                .token(token)
                .expiredIn(expirationTime)
                .build();
    }
}
