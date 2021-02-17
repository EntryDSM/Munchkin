package kr.hs.entrydsm.common.context.auth.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenExpiration;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenExpiration;

    @Value("${auth.jwt.header.access}")
    private String accessTokenHeader;

    @Value("${auth.jwt.header.refresh}")
    private String refreshTokenHeader;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    public String generateAccessToken(Integer receiptCode) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .setSubject(receiptCode.toString())
                .claim("type", "access_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(Integer receiptCode) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000))
                .setSubject(receiptCode.toString())
                .claim("type", "refresh_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(accessTokenHeader);
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length() + 1);
        }
        return null;
    }

    public String resoleRefreshToken(HttpServletRequest request) {
        return request.getHeader(refreshTokenHeader);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long parseAccessToken(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject());
    }

    public boolean isRefreshToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().get("type").equals("refresh_token");
    }

}
