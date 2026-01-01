package com.example.socialgraphengine.helper;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Getter

public class JwtService {

    @Value("${jwt.secret}")
    private  String secret;

    @Value("${jwt.expiration}")
    private  Long expiration;

    @Value("${jwt.refresh-expiration}")
    private  Long refresh;


    // ====== generate secret key ===========
 private SecretKey getSigningKey() {
     return  Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
 }


// ========= Generate Token ================

public String generateToken(UserDetails user) {

    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", user.getAuthorities());

    return doGenerateToken(claims , user.getUsername());

}

private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .signWith(getSigningKey())
                .subject(username)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
}

public String refreshToken(String username)
{
    return Jwts.builder()
            .signWith(getSigningKey())
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + refresh))
            .compact();
}


// ============= Extract Token ==========

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public  <T> T extractClaim(String token , Function<Claims, T> claimsTFunction)
    {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }




    // ============ Validate Token ============

    public boolean validateToken(String token)
    {
      try {
          Jwts.parser()
                  .verifyWith(getSigningKey())
                  .build()
                  .parseSignedClaims(token);

          return true;
      } catch (JwtException e)
      {
          return false;
      }

    }

    public boolean validateToken(String token , UserDetails user)
    {
       String username = extractUsername(token);
       return username.equals(user.getUsername()) && !isTokenExpire(token);
    }

    public boolean isTokenExpire(String token)
    {
        return extractClaim(token, Claims::getExpiration).before(new Date(System.currentTimeMillis()));
    }



  
}
