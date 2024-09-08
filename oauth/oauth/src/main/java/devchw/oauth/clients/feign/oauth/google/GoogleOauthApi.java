package devchw.oauth.clients.feign.oauth.google;

import devchw.oauth.clients.feign.oauth.google.dto.GoogleToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "google-oauth-api", url = "https://oauth2.googleapis.com")
interface GoogleOauthApi {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    GoogleToken getGoogleToken(@RequestParam MultiValueMap<String, String> params);

}
