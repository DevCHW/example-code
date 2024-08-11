package devchw.oauth.client.x;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
record XUser(Data data) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record Data(
            String id,
            String username,
            String profileImageUrl,
            String name
    ) {

    }
}
