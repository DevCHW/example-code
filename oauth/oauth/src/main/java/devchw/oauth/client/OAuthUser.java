package devchw.oauth.client;

import devchw.oauth.enums.OAuthServerType;
import lombok.Builder;

@Builder
public record OAuthUser(
        OAuthServerType oAuthServerType,
        String oauthId,
        String email,
        String profileImage,
        String username
) {
}
