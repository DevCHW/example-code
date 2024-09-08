package devchw.oauth.api.domain;

import devchw.oauth.global.enums.OauthServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthServiceTypeAndOauthId(OauthServiceType oAuthServiceType, String oauthId);

}
