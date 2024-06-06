package com.dev.explore_blog.security;

import com.dev.explore_blog.exception.ExploreApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;

    // generate jwt token
    public String generateJwtToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        // generate token
        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(key())
                .compact();

    }

    // generate customized key
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from token
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException malformedJwtException){
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        }catch (ExpiredJwtException expiredJwtException){
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        }catch (UnsupportedJwtException unsupportedJwtException){
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        }catch (IllegalArgumentException illegalArgumentException){
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, "Jwt claims string is null or empty");
        }catch (Exception exception){
            throw new ExploreApiException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

}
