package devchw.oauth.security.jwt;

import devchw.oauth.security.CustomUserDetails;
import devchw.oauth.storage.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Objects;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    // Constructor
    public JwtTokenProvider(Environment env) {
        String key = env.getProperty("app.jwt.secret");
        this.secretKey = Keys.hmacShaKeyFor(Objects.requireNonNull(key).getBytes());
    }

    // create jwt
    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        Long id = Long.valueOf(claims.get("id").toString());
        String name = claims.get("username").toString();
        String email = claims.get("email").toString();

        CustomUserDetails userDetails = CustomUserDetails.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    // validate token
    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }
        parseClaims(token);
        return true;
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("토큰 만료");
        } catch (MalformedJwtException | SecurityException e) {
            throw new RuntimeException("잘못된 토큰 정보");
        }
    }

}
