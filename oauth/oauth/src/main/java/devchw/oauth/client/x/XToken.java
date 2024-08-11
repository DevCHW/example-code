package devchw.oauth.client.x;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record XToken(
        String accessToken,
        String tokenType,
        String scope,
        Long expiresIn
) {
}
