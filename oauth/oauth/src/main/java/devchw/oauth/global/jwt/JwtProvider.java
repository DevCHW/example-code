package devchw.oauth.global.jwt;

import devchw.oauth.api.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private static final String TOKEN_TYPE_BEARER = "Bearer";

    // constructor
    public JwtProvider(Environment env) {
        String key = env.getProperty("jwt.secret");
        this.secretKey = Keys.hmacShaKeyFor(Objects.requireNonNull(key).getBytes());
    }

    // create jwt
    public JwtTokenInfo generateToken(User user) {
        long issuedAt = System.currentTimeMillis();
        String accessToken = generateAccessToken(user, issuedAt);
        return JwtTokenInfo.builder()
                .tokenType(TOKEN_TYPE_BEARER)
                .accessToken(accessToken)
                .build();
    }

    // create access token
    private String generateAccessToken(User user, long issuedAt) {
        Map<String, Object> claims = new ConcurrentHashMap<>();
        claims.put("id", user.getId());
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setClaims(claims)
                .setIssuedAt(new Date(issuedAt))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
