package kr.hs.entrydsm.admin.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.entrydsm.admin.security.auth.AuthDetails;
import kr.hs.entrydsm.admin.security.auth.AuthDetailsService;
import kr.hs.entrydsm.admin.usecase.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenExpiration;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenExpiration;

    @Value("${auth.jwt.header}")
    private String header;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .claim("type", "access_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000))
                .claim("type", "refresh_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(header);
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public String getEmail(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = authDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    public boolean isRefreshToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("type").equals("refresh_token");
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

}
