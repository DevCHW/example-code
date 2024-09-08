package devchw.oauth.clients.feign.oauth.discord;

import devchw.oauth.clients.feign.oauth.OauthClient;
import devchw.oauth.clients.feign.oauth.discord.dto.DiscordToken;
import devchw.oauth.clients.feign.oauth.discord.dto.DiscordTokenRequest;
import devchw.oauth.clients.feign.oauth.discord.dto.DiscordUser;
import devchw.oauth.clients.feign.oauth.OauthResult;
import devchw.oauth.global.enums.OauthServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordOauthClient implements OauthClient {

    private final DiscordOauthConfig discordOAuthConfig;
    private final DiscordApi discordApi;

    @Override
    public OauthServiceType supportService() {
        return OauthServiceType.DISCORD;
    }

    @Override
    public OauthResult getOAuthUser(String code, String redirectUri) {
        DiscordTokenRequest param = generateTokenRequestParam(code, redirectUri);
        DiscordToken discordToken = discordApi.getDiscordToken(param.toBodyString());
        DiscordUser discordUser = discordApi.getDiscordUser("Bearer " + discordToken.accessToken());

        return OauthResult.builder()
                .oauthServiceType(OauthServiceType.DISCORD)
                .oauthId(discordUser.id())
                .email(discordUser.email())
                .profileImage(discordUser.avatar())
                .build();
    }

    private DiscordTokenRequest generateTokenRequestParam(String code, String redirectUri) {
        return DiscordTokenRequest.builder()
                .grantType("authorization_code")
                .redirectUri(redirectUri)
                .clientId(discordOAuthConfig.clientId())
                .clientSecret(discordOAuthConfig.clientSecret())
                .code(code)
                .build();
    }

}
