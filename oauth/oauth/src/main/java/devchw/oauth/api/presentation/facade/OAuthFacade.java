package devchw.oauth.api.presentation.facade;

import devchw.oauth.api.domain.OAuthService;
import devchw.oauth.api.presentation.controller.dto.response.OAuthConnectResponse;
import devchw.oauth.api.presentation.controller.dto.response.OAuthLoginPageUrlResponse;
import devchw.oauth.api.presentation.controller.dto.response.OAuthLoginResponse;
import devchw.oauth.client.OAuthClientComposite;
import devchw.oauth.client.OAuthUser;
import devchw.oauth.enums.OAuthServerType;
import devchw.oauth.security.jwt.JwtTokenProvider;
import devchw.oauth.storage.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthFacade {

    private final OAuthService oauth2Service;
    private final OAuthClientComposite oAuthClientComposite;
    private final JwtTokenProvider jwtTokenProvider;

    public OAuthLoginPageUrlResponse getOAuthLoginPageUrl(OAuthServerType oauthServerType) {
        String loginPageUrl = oAuthClientComposite.getOAuthLoginPageUrl(oauthServerType);
        return new OAuthLoginPageUrlResponse(loginPageUrl);
    }

    public OAuthLoginResponse login(String code, OAuthServerType oauthServerType) {
        OAuthUser oAuthUser = oAuthClientComposite.getOAuthUser(oauthServerType, code);
        UserEntity userEntity = oauth2Service.login(oAuthUser);
        String accessToken = jwtTokenProvider.generateToken(userEntity);
        return new OAuthLoginResponse(accessToken);
    }

    public OAuthConnectResponse connectOAuthAccount(String code, OAuthServerType oauthServerType) {
        OAuthUser oAuthUser = oAuthClientComposite.getOAuthUser(oauthServerType, code);
        return null;
    }

}
