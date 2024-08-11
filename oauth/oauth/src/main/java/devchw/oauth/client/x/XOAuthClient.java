package devchw.oauth.client.x;

import devchw.oauth.client.OAuthClient;
import devchw.oauth.client.OAuthUser;
import devchw.oauth.enums.OAuthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class XOAuthClient implements OAuthClient {

    private final XApi xApi;
    private final XOAuthConfig xoAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.X;
    }

    @Override
    public OAuthUser getOAuthUser(String code) {
        XToken xToken = xApi.getXToken(tokenRequestParams(code));
        XUser xUser = xApi.getXUser("Bearer " + xToken.accessToken(), getOAuthUserRequestParam());

        return OAuthUser.builder()
                .oAuthServerType(OAuthServerType.X)
                .oauthId(xUser.data().id())
                .profileImage(xUser.data().profileImageUrl())
                .username(xUser.data().username())
                .build();
    }

    @Override
    public String getLoginPageUrl() {
        return UriComponentsBuilder
                .fromUriString("https://twitter.com/i/oauth2/authorize")
                .queryParam("response_type", "code")
                .queryParam("code_challenge", "challenge")
                .queryParam("code_challenge_method", "plain")
                .queryParam("client_id", xoAuthConfig.clientId())
                .queryParam("redirect_uri", xoAuthConfig.redirectUri())
                .queryParam("scope", String.join("+", xoAuthConfig.scope()))
                .queryParam("state", UUID.randomUUID().toString())
                .toUriString();
    }

    private MultiValueMap<String, String> tokenRequestParams(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", xoAuthConfig.clientId());
        params.add("redirect_uri", xoAuthConfig.redirectUri());
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
