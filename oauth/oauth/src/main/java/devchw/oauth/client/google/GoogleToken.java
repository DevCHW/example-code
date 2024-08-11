package devchw.oauth.client.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GoogleToken(
        String accessToken,
        String tokenType,
        String scope,
        String idToken,
        Long expiresIn
) {
}
