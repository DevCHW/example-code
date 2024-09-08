package devchw.oauth.global.enums;

import java.util.Locale;

public enum OauthServiceType {
    GOOGLE, X, DISCORD;

    public static OauthServiceType fromName(String type) {
        return OauthServiceType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
