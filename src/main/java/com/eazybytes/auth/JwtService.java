package com.eazybytes.auth;

import com.eazybytes.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-hours}")
    private Long jwtExpiration;

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email",String.class));
    }

    public List<String> extractRoles(String token) {
        List<?> roles = extractClaim(token, claims -> claims.get("roles", List.class));
        return roles.stream().map(Object::toString).toList();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }


    private Claims extractAllClaims(String token) {

        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(CustomUserDetails customUserDetails) {
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("email", customUserDetails.getUsername());
        hashMap.put("roles", customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return generateToken(hashMap, customUserDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, CustomUserDetails customUserDetails) {
        return Jwts.builder()
                .claims()
                .add(extraClaims)
                .subject(customUserDetails.getCustomer().getId().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(jwtExpiration)))
                .and()
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isValidToken(String token, CustomUserDetails customUserDetails) {
        String extractedEmail = extractEmail(token);
        String email = customUserDetails.getCustomer().getEmail();
        List<String> extractedRoles = extractRoles(token);
        List<String> roles = customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return (email.equals(extractedEmail) && roles.equals(extractedRoles) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
