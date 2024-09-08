package devchw.oauth.api.controller.dto.response;

import lombok.Builder;

@Builder
public record OAuthLoginTokenResponse(
        String tokenType,
        String accessToken,
        String refreshToken,
        Long expiredAt
) {
}
