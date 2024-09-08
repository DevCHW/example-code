package devchw.oauth.clients.feign.oauth.google;

import devchw.oauth.clients.feign.oauth.OauthClient;
import devchw.oauth.clients.feign.oauth.OauthResult;
import devchw.oauth.clients.feign.oauth.google.dto.GoogleToken;
import devchw.oauth.clients.feign.oauth.google.dto.GoogleUser;
import devchw.oauth.global.enums.OauthServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class GoogleOauthClient implements OauthClient {

    private final GoogleOauthConfig googleOAuthConfig;
    private final GoogleApi googleApi;
    private final GoogleOauthApi googleOAuthApi;

    @Override
    public OauthServiceType supportService() {
        return OauthServiceType.GOOGLE;
    }

    @Override
    public OauthResult getOAuthUser(String code, String redirectUri) {
        MultiValueMap<String, String> params = generateTokenRequestParams(code, redirectUri);
        GoogleToken token = googleOAuthApi.getGoogleToken(params);
        GoogleUser googleUser = googleApi.getGoogleUser("Bearer " + token.accessToken());

        return OauthResult.builder()
                .oauthId(googleUser.id())
                .oauthServiceType(OauthServiceType.GOOGLE)
                .email(googleUser.email())
                .profileImage(googleUser.picture())
                .build();
    }

    private MultiValueMap<String, String> generateTokenRequestParams(String code, String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleOAuthConfig.clientId());
        params.add("redirect_uri", redirectUri);
        params.add("client_secret", googleOAuthConfig.clientSecret());
        params.add("code", code);
        return params;
    }

}
