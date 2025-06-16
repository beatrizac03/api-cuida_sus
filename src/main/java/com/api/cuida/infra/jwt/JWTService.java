package com.api.cuida.infra.jwt;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Gera e valida tokens JWT
 */
@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // public String generateToken(String cpf) {
    // Map<String, Object> claims = new HashMap<>();
    // return createToken(claims, cpf);
    // }

    public String generateToken(String identificador, String tipoUsuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tipo", tipoUsuario); // "PACIENTE" ou "FUNCIONARIO"
        return createToken(claims, identificador);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // cpf ou matrícula
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // public String extractCpf(String token) {
    // return extractClaim(token, Claims::getSubject);
    // }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // public boolean isTokenValid(String token, String subject) {
    // final String tokenSubject = extractSubject(token);
    // return (tokenSubject.equals(subject) && !isTokenExpired(token));
    // }

    // Extrai o tipo de usuário: "PACIENTE" ou "FUNCIONARIO"
    public String extractTipo(String token) {
        return extractClaim(token, claims -> (String) claims.get("tipo"));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        // Verifica se o token é válido para um determinado identificador
    public boolean isTokenValid(String token, String identificadorEsperado) {
        final String tokenIdentificador = extractSubject(token);
        return (tokenIdentificador.equals(identificadorEsperado) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
