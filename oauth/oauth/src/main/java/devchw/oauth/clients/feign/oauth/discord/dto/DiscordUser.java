package devchw.oauth.clients.feign.oauth.discord.dto;

import lombok.Builder;

@Builder
public record DiscordUser(
    String id,
    String username,
    String discriminator,
    String globalName,
    String avatar,
    Integer publicFlags,
    Integer flags,
    String banner,
    Integer accentColor,
    String avatarDecorationData,
    String bannerColor,
    String clan,
    Boolean mfaEnabled,
    String locale,
    Integer premiumType,
    String email,
    Boolean verified
) {
}
