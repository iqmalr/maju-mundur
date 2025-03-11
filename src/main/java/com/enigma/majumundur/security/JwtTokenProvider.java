package com.enigma.majumundur.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.majumundur.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${enigmashop.secret-key}")
    private String SECRET_KEY;
    @Value("${enigmashop.expiration}")
    private Long EXPIRATION_TIME;
    public String getUserNameFromToken(String token){
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getSubject();
    }
    public Boolean validateToken(String token){
        try {
            DecodedJWT decodedJWT=getDecodedJWT(token);
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
    public String getRoleFromToken(String token){
        DecodedJWT decodedJWT=getDecodedJWT(token);
        String role=decodedJWT.getClaim("role").asString();

        if (role.startsWith("[")&&role.endsWith("]")){
            role=role.substring(1, role.length()-1);
        }
        System.out.println("DEBUG ROLE : "+role);
        return role;
    }
    public String generateToken(String username, String role){
        String token=JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis()+ Constant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
        return token;
    }
    public Long getExpirationTime(String token){
        DecodedJWT decodedJWT=getDecodedJWT(token);
        Date expirationDate=decodedJWT.getExpiresAt();
        return expirationDate.getTime()-System.currentTimeMillis();
    }
    private DecodedJWT getDecodedJWT(String token){
        DecodedJWT decodedJWT= JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);
        return decodedJWT;
    }

}
