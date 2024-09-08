package devchw.oauth.clients.feign.oauth.x;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.x")
public record XOauthConfig(
        String clientId,
        String clientSecret,
        String[] scope
) {
}
