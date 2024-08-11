package devchw.oauth.client.google;

import devchw.oauth.client.OAuthUser;
import devchw.oauth.client.OAuthClient;
import devchw.oauth.enums.OAuthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GoogleOAuthClient implements OAuthClient {

    private final GoogleOAuthConfig googleOAuthConfig;
    private final GoogleApi googleApi;
    private final GoogleOAuthApi googleOAuthApi;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.GOOGLE;
    }

    @Override
    public OAuthUser getOAuthUser(String code) {
        MultiValueMap<String, String> params = tokenRequestParams(code);
        GoogleToken token = googleOAuthApi.getGoogleToken(params);
        GoogleUser googleUser = googleApi.getGoogleUser("Bearer " + token.accessToken());

        String email = googleUser.email();
        String username = email.substring(0, email.indexOf("@"));

        return OAuthUser.builder()
                .oauthId(googleUser.id())
                .oAuthServerType(OAuthServerType.GOOGLE)
                .email(googleUser.email())
                .profileImage(googleUser.picture())
                .username(username)
                .build();
    }

    @Override
    public String getLoginPageUrl() {
        return UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount")
                .queryParam("response_type", "code")
                .queryParam("client_id", googleOAuthConfig.clientId())
                .queryParam("redirect_uri", googleOAuthConfig.redirectUri())
                .queryParam("scope", String.join(" ", googleOAuthConfig.scope()))
                .queryParam("state", UUID.randomUUID().toString())
                .toUriString();
    }

    private MultiValueMap<String, String> tokenRequestParams(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleOAuthConfig.clientId());
        params.add("redirect_uri", googleOAuthConfig.redirectUri());
        params.add("client_secret", googleOAuthConfig.clientSecret());
        params.add("code", code);
        return params;
    }

}
