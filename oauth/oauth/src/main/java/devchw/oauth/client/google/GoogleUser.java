package devchw.oauth.client.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
record GoogleUser(
        String id,
        String email,
        String verifiedEmail,
        String name,
        String givenName,
        String familyName,
        String picture
) {
}
