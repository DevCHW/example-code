package devchw.oauth.clients.feign.oauth.google.dto;

import lombok.Builder;

@Builder
public record GoogleUser(
        String id,
        String email,
        String verifiedEmail,
        String name,
        String givenName,
        String familyName,
        String picture
) {
}
