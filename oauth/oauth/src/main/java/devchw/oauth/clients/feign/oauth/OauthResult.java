package devchw.oauth.clients.feign.oauth;

import devchw.oauth.global.enums.OauthServiceType;
import lombok.Builder;

@Builder
public record OauthResult(
        OauthServiceType oauthServiceType,
        String oauthId,
        String email,
        String profileImage
) {
}
