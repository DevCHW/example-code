package devchw.oauth.client;

import devchw.oauth.enums.OAuthServerType;

public interface OAuthClient {
    OAuthServerType supportServer();

    OAuthUser getOAuthUser(String code);

    String getLoginPageUrl();
}
