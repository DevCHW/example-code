package devchw.oauth.client.discord;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DiscordToken(
        String tokenType,
        String accessToken,
        Long expiresIn,
        String refreshToken,
        String scope
) {
}
