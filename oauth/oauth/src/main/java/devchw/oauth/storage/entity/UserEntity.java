package devchw.oauth.storage.entity;

import devchw.oauth.enums.OAuthServerType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "register_service_type", nullable = false)
    private OAuthServerType registerServiceType;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "x_id")
    private String xId;

    @Builder
    public UserEntity(String email, String username, String password, String profileImage, OAuthServerType registerServiceType, String googleId, String xId) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
        this.registerServiceType = registerServiceType;
        this.googleId = googleId;
        this.xId = xId;
    }
}
