package devchw.oauth.api.domain;

import devchw.oauth.client.OAuthUser;
import devchw.oauth.storage.entity.UserEntity;
import devchw.oauth.storage.repository.UserEntityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final UserEntityJpaRepository userEntityJpaRepository;

    /**
     * 로그인
     */
    public UserEntity login(OAuthUser oAuthUser) {
        return findOAuthUser(oAuthUser)
                .orElseGet(() -> userEntityJpaRepository.save(UserEntity.builder()
                        .email(oAuthUser.email())
                        .username(oAuthUser.username())
                        .profileImage(oAuthUser.profileImage())
                        .registerServiceType(oAuthUser.oAuthServerType())
                        .googleId(oAuthUser.oauthId())
                        .xId(oAuthUser.oauthId())
                        .build()));
    }

    // 소셜 연동 유저 조회
    private Optional<UserEntity> findOAuthUser(OAuthUser oAuthUser) {
        return switch (oAuthUser.oAuthServerType()) {
            case GOOGLE -> userEntityJpaRepository.findByGoogleId(oAuthUser.oauthId());
            case X -> userEntityJpaRepository.findByXId(oAuthUser.oauthId());
            case DISCORD -> Optional.empty();
        };
    }

}
