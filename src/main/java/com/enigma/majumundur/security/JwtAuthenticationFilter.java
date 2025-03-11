package com.enigma.majumundur.security;

//import com.enigma.majumundur.service.impl.RedisTokenBlacklistService;
import com.enigma.majumundur.service.impl.RedisTokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider tokenProvider;
    private final RedisTokenBlacklistService redisTokenBlacklistService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {

        String token=extractTokenFromRequest(httpServletRequest);
        if (token==null){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (redisTokenBlacklistService.isTokenBlacklisted(token)){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Token has been logged out");
            return;
        }
        try {
            if (tokenProvider.validateToken(token)){
                String username=tokenProvider.getUserNameFromToken(token);
                String role=tokenProvider.getRoleFromToken(token);
                List<GrantedAuthority> grantedAuthorityList=List.of(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, null, grantedAuthorityList);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Invalid or expired token");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    public String extractTokenFromRequest(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        if (header==null||!header.startsWith("Bearer ")){
            return null;
        }
        return header.substring(7);
    }
}
