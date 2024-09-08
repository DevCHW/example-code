package devchw.oauth.clients.feign.oauth.discord;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.discord")
public record DiscordOauthConfig(
        String clientId,
        String clientSecret,
        String[] scope
) {
}
