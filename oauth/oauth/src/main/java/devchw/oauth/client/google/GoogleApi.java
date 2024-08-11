package devchw.oauth.client.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "google-api", url = "https://www.googleapis.com")
interface GoogleApi {

    @GetMapping(value = "/oauth2/v1/userinfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    GoogleUser getGoogleUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
