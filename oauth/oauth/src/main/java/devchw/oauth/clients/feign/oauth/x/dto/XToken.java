package devchw.oauth.clients.feign.oauth.x.dto;

public record XToken(
        String accessToken,
        String tokenType,
        String scope,
        Long expiresIn
) {
}
