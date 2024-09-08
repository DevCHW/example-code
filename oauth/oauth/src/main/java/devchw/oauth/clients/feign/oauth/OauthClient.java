package devchw.oauth.clients.feign.oauth;

import devchw.oauth.global.enums.OauthServiceType;

public interface OauthClient {
    OauthServiceType supportService();

    OauthResult getOAuthUser(String code, String redirectUri);

}
