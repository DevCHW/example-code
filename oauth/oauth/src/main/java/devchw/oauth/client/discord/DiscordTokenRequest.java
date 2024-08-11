package devchw.oauth.client.discord;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DiscordTokenRequest(
        String grantType,
        String clientId,
        String redirectUri,
        String clientSecret,
        String code
) {
    public String toBodyString() {
        return  "code=" + code + '&' +
                "redirect_uri=" + redirectUri + '&' +
                "client_id=" + clientId + '&' +
                "client_secret=" + clientSecret + '&' +
                "grant_type=" + grantType;
    }
}
