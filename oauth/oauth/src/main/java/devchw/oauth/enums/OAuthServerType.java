package devchw.oauth.enums;

import java.util.Locale;

public enum OAuthServerType {
    GOOGLE, X, DISCORD;

    public static OAuthServerType fromName(String type) {
        return OAuthServerType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
