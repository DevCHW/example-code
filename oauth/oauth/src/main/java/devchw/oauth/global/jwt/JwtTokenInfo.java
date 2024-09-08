package devchw.oauth.global.jwt;

import lombok.Builder;

@Builder
public record JwtTokenInfo(
        String tokenType,
        String accessToken
) {
}
