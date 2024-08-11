package devchw.oauth.client.x;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.x")
public record XOAuthConfig(
        String redirectUri,
        String clientId,
        String clientSecret,
        String[] scope
) {
}
