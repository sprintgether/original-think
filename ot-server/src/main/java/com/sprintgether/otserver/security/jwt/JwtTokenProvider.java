package com.sprintgether.otserver.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration}")
    private int jwtExpiration;

    /**
     * Generate a jwt access token
     * @param email
     * @return generated jwt token
     */
    public String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Validate an incoming jwt token
     * @param authToken
     * @return true if the token is valid and false if not
     * @throws SignatureException
     * @throws MalformedJwtException
     * @throws UnsupportedJwtException
     * @throws IllegalArgumentException
     * @throws ExpiredJwtException
     */
    public boolean validateJwtToken(String authToken) throws SignatureException, MalformedJwtException, UnsupportedJwtException, IllegalArgumentException, ExpiredJwtException {
        Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken);
        return true;
    }

    /**
     * Extract email of a User from a jwt token
     * @param token
     * @return the email of the current User connected
     */
    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
