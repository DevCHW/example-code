package devchw.oauth.client.discord;

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
public class DiscordOAuthClient implements OAuthClient {

    private final DiscordOAuthConfig discordOAuthConfig;
    private final DiscordApi discordApi;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.DISCORD;
    }

    @Override
    public OAuthUser getOAuthUser(String code) {
        DiscordTokenRequest param = generateRequestParam(code);
        DiscordToken discordToken = discordApi.getDiscordToken(param.toBodyString());
        DiscordUser discordUser = discordApi.getDiscordUser("Bearer " + discordToken.accessToken());

        return OAuthUser.builder()
                .oAuthServerType(OAuthServerType.DISCORD)
                .oauthId(discordUser.id())
                .email(discordUser.email())
                .profileImage(discordUser.avatar())
                .username(discordUser.username())
                .build();
    }

    private DiscordTokenRequest generateRequestParam(String code) {
        return DiscordTokenRequest.builder()
                .grantType("authorization_code")
                .redirectUri(discordOAuthConfig.redirectUri())
                .clientId(discordOAuthConfig.clientId())
                .clientSecret(discordOAuthConfig.clientSecret())
                .code(code)
                .build();
    }

    @Override
    public String getLoginPageUrl() {
        return UriComponentsBuilder
                .fromUriString("https://discord.com/oauth2/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", discordOAuthConfig.clientId())
                .queryParam("redirect_uri", discordOAuthConfig.redirectUri())
                .queryParam("scope", String.join(" ", discordOAuthConfig.scope()))
                .queryParam("state", UUID.randomUUID().toString())
                .toUriString();
    }

    private MultiValueMap<String, String> tokenRequestParams(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", discordOAuthConfig.redirectUri());
        params.add("code", code);
        return params;
    }

}
