package devchw.oauth.clients.feign.oauth.discord.dto;

public record DiscordToken(
        String tokenType,
        String accessToken,
        Long expiresIn,
        String refreshToken,
        String scope
) {
}
