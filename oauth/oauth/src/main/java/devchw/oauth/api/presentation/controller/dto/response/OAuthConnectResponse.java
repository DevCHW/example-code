package devchw.oauth.api.presentation.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OAuthConnectResponse(
        Long userId,
        String connectOauthId
) {
}
