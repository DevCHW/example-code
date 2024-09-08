package devchw.oauth.clients.feign.oauth.google;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.google")
public record GoogleOauthConfig(
        String clientId,
        String clientSecret,
        String[] scope
) {
}
