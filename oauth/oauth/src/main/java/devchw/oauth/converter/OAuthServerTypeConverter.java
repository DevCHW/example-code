package devchw.oauth.converter;

import devchw.oauth.enums.OAuthServerType;
import org.springframework.core.convert.converter.Converter;

public class OAuthServerTypeConverter implements Converter<String, OAuthServerType> {

    @Override
    public OAuthServerType convert(String source) {
        return OAuthServerType.fromName(source);
    }

}
