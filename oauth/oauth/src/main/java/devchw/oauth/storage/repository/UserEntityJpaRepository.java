package devchw.oauth.storage.repository;

import devchw.oauth.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserEntityJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByGoogleId(String googleId);

    @Query("select u from UserEntity u where u.xId = :xId")
    Optional<UserEntity> findByXId(@Param("xId") String xId);

}
