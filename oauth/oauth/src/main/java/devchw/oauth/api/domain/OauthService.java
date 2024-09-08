package devchw.oauth.api.domain;

import devchw.oauth.api.controller.dto.response.OAuthLoginTokenResponse;
import devchw.oauth.clients.feign.oauth.OauthClientComposite;
import devchw.oauth.clients.feign.oauth.OauthResult;
import devchw.oauth.global.enums.OauthServiceType;
import devchw.oauth.global.jwt.JwtProvider;
import devchw.oauth.global.jwt.JwtTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthService {

    private final OauthClientComposite oAuthClientComposite;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    /**
     * OAuth 로그인 OR 회원가입
     */
    public OAuthLoginTokenResponse loginOrSignUp(String code, OauthServiceType oAuthServiceType, String redirectUri) {
        OauthResult result = oAuthClientComposite.getOAuthResult(oAuthServiceType, code, redirectUri);

        User user = userRepository.findByOauthServiceTypeAndOauthId(result.oauthServiceType(), result.oauthId())
                .orElseGet(() -> userRepository.save(User.builder()
                        .oAuthServiceType(result.oauthServiceType())
                        .oauthId(result.oauthId())
                        .build()));

        // 토큰 발급
        JwtTokenInfo token = jwtProvider.generateToken(user);
        return OAuthLoginTokenResponse.builder()
                .tokenType(token.tokenType())
                .accessToken(token.accessToken())
                .build();
    }

}
