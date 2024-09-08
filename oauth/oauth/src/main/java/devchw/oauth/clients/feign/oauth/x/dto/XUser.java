package devchw.oauth.clients.feign.oauth.x.dto;

import lombok.Builder;

public record XUser(Data data) {

    @Builder
    public record Data(
            String id,
            String username,
            String profileImageUrl,
            String name
    ) {
    }
}
