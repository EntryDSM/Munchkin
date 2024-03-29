package kr.hs.entrydsm.common.context.auth.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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

    @Value("${auth.jwt.prefix}")
    private String prefix;

    public String generateAccessToken(Long receiptCode) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .setSubject(receiptCode.toString())
                .claim("type", "access_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(Long receiptCode) {
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
        if (bearerToken != null && bearerToken.startsWith(prefix)
		&& bearerToken.length() > prefix.length() + 1) {
            return bearerToken.substring(prefix.length() + 1);
        }
        return null;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("x-refresh-token");
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

    public long parseRefreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        if (claims.get("type").equals("refresh_token")) {
            return Long.parseLong(claims.getSubject());
        }
        throw new MunchkinException(ErrorCode.INVALID_TOKEN);
    }

    public String parseAdminToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isRefreshToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().get("type").equals("refresh_token");
    }

}
