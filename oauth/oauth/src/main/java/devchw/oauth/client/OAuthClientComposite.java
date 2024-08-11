package devchw.oauth.client;

import devchw.oauth.enums.OAuthServerType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OAuthClientComposite {

    private final Map<OAuthServerType, OAuthClient> mapping;

    public OAuthClientComposite(Set<OAuthClient> clients) {
        mapping = clients.stream()
                .collect(toMap(
                        OAuthClient::supportServer,
                        identity()
                ));
    }

    public OAuthUser getOAuthUser(OAuthServerType oauthServerType, String code) {
        return getClient(oauthServerType).getOAuthUser(code);
    }

    public String getOAuthLoginPageUrl(OAuthServerType oAuthServerType) {
        return getClient(oAuthServerType).getLoginPageUrl();
    }

    private OAuthClient getClient(OAuthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType))
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));
    }
}
