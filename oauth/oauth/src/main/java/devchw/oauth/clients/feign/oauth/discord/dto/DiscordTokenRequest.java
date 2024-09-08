package devchw.oauth.clients.feign.oauth.discord.dto;

import lombok.Builder;

@Builder
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
