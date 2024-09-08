package devchw.oauth.clients.feign.oauth;

import devchw.oauth.global.enums.OauthServiceType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OauthClientComposite {

    private final Map<OauthServiceType, OauthClient> mapping;

    public OauthClientComposite(Set<OauthClient> clients) {
        mapping = clients.stream()
                .collect(toMap(
                        OauthClient::supportService,
                        identity()
                ));
    }

    public OauthResult getOAuthResult(OauthServiceType oAuthServiceType, String code, String redirectUri) {
        return getOAuthClient(oAuthServiceType).getOAuthUser(code, redirectUri);
    }

    private OauthClient getOAuthClient(OauthServiceType oAuthServiceType) {
        OauthClient oAuthClient = mapping.get(oAuthServiceType);
        if (Objects.isNull(oAuthClient)) {
            throw new IllegalArgumentException("This OAuth service type is not supported.");
        }
        return oAuthClient;
    }
}
