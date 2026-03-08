package com.cap.BookStroreRest.Config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtUtil {
    private final String SECRET_KEY="bdndedewncewbknlmdncbwhkedqmcnebkhvwbdnqm";
    private final int REFERESH_EXPIRATION=900000;
    private final int JWT_EXPIRATION=60000000;


    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email){
        return generateToken(email,JWT_EXPIRATION);
    }

    public String generateRefereshToken(String email){
        return generateToken(email, REFERESH_EXPIRATION);
    }

    private String generateToken(String email, long expiration){
        return jwts.builder().setSubject(email)
                .setIssuedAt(new Date())
                .
    }
}
