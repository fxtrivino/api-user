package com.bci.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtUtil {

    // Generar una clave secreta de manera segura
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Método para generar el JWT
    public static String generateToken(String username) {
        long expirationTime = 1000 * 60 * 60; // 1 hora de validez
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        return Jwts.builder()
                .setSubject(username)              // Usuario como 'subject'
                .setIssuedAt(new Date())           // Fecha de emisión
                .setExpiration(expirationDate)     // Fecha de expiración
                .signWith(SECRET_KEY)              // Firma usando la clave secreta
                .compact();                       // Compilamos el JWT en un string
    }

    // Método para verificar el JWT
    public static Claims parseToken(String token) {
        try {
            // Creamos el parser con la clave secreta
            return Jwts.parser()  // Aquí debe funcionar ahora
                    .setSigningKey(SECRET_KEY)  // Establecemos la clave secreta
                    .build()
                    .parseClaimsJws(token)     // Analizamos el JWT
                    .getBody();                // Extraemos el cuerpo del JWT
        } catch (Exception e) {
            log.error("Error al verificar el token: {}" , e.getMessage());
        }
        return null;
    }

    
}






