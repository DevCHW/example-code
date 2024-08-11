package devchw.oauth.client.discord;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.discord")
public record DiscordOAuthConfig(
        String redirectUri,
        String clientId,
        String clientSecret,
        String[] scope
) {
}
