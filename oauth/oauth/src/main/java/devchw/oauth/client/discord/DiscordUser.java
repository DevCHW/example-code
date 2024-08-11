package devchw.oauth.client.discord;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
