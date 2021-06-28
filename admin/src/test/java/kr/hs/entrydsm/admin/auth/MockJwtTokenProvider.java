package kr.hs.entrydsm.admin.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.entrydsm.admin.security.JwtTokenProvider;

import java.util.Date;

public class MockJwtTokenProvider extends JwtTokenProvider {

    @Override
    public String generateRefreshToken(String id) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 123456 * 1000))
                .setSubject(id)
                .claim("type", "refresh_token")
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
    }

}
