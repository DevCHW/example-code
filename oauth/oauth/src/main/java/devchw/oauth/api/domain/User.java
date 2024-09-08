package devchw.oauth.api.domain;

import devchw.oauth.global.enums.OauthServiceType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users",
        uniqueConstraints={
                @UniqueConstraint(
                        name="oauth_unique",
                        columnNames={"oauthServiceType", "oauthId"}
                )}
)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OauthServiceType oauthServiceType;

    private String oauthId;

    @Builder
    public User(String email, String name, OauthServiceType oAuthServiceType, String oauthId) {
        this.oauthServiceType = oAuthServiceType;
        this.oauthId = oauthId;
    }

}
