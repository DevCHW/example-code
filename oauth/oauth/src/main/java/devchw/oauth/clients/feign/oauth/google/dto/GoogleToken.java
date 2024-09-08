package devchw.oauth.clients.feign.oauth.google.dto;

public record GoogleToken(
        String accessToken,
        String tokenType,
        String scope,
        String idToken,
        Long expiresIn
) {
}
