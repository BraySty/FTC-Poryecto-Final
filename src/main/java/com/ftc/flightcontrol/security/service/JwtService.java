package com.ftc.flightcontrol.security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    /**
     * Metodo que regresa un String con el token.
     * @param user UserDetails del ususario.
     * @return Regresa el token como String.
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Metodo que crea el token.
     * @param extraClaims Map que contiene informacion adicional.
     * @param user UserDetails del ususario.
     * @return Regresa el token como String.
     */
    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        extraClaims.put("role", user.getAuthorities());
        extraClaims.put("username", user.getUsername());
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Metodo que crea la clave Key.
     * @return Regresa la clave como Key.
     */
    private Key getKey() {
       byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Metodo que regresa el nombre de usuario a partir de un token.
     * @param token El token como String
     * @return String con el nombre de usuario.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * 
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

}
