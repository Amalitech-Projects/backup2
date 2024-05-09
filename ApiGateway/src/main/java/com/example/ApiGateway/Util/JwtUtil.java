package com.example.ApiGateway.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public void validateToken(final String token) {
        Jwts.parser().decryptWith(getSignKey()).build().parseSignedClaims(token.substring(7));
    }


    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getClaims(String token) {
        return Jwts.parser().decryptWith(getSignKey()).build().parseSignedClaims(token.substring(7)).getPayload();
    }

    public boolean isExpired(String token) {
        try {
            return getClaims(token.substring(7)).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEx(String token){
        long issuedDate = getClaims(token).getIssuedAt().getTime();
        long expiredDate = getClaims(token).getExpiration().getTime();
        System.out.println(issuedDate + " and expires at: " + expiredDate);

        return true;
    }
}