package devchw.oauth.clients.feign.oauth.x;

import devchw.oauth.clients.feign.oauth.OauthClient;
import devchw.oauth.clients.feign.oauth.OauthResult;
import devchw.oauth.clients.feign.oauth.x.dto.XToken;
import devchw.oauth.clients.feign.oauth.x.dto.XUser;
import devchw.oauth.global.enums.OauthServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class XOauthClient implements OauthClient {

    private final XApi xApi;
    private final XOauthConfig xoAuthConfig;

    @Override
    public OauthServiceType supportService() {
        return OauthServiceType.X;
    }

    @Override
    public OauthResult getOAuthUser(String code, String redirectUri) {
        XToken xToken = xApi.getXToken(generateTokenRequestParams(code, redirectUri));
        XUser xUser = xApi.getXUser("Bearer " + xToken.accessToken(), getOAuthUserRequestParam());

        return OauthResult.builder()
                .oauthServiceType(OauthServiceType.X)
                .oauthId(xUser.data().id())
                .profileImage(xUser.data().profileImageUrl())
                .build();
    }

    private MultiValueMap<String, String> generateTokenRequestParams(String code, String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", xoAuthConfig.clientId());
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("code_verifier", "challenge");
        return params;
    }

    private MultiValueMap<String, String> getOAuthUserRequestParam() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user.fields", "profile_image_url");
        return params;
    }

}
