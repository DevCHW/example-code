package devchw.oauth.clients.feign.oauth.x;

import devchw.oauth.clients.feign.oauth.x.dto.XToken;
import devchw.oauth.clients.feign.oauth.x.dto.XUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "x-api", url = "https://api.twitter.com")
interface XApi {

    @GetMapping(value = "/2/users/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    XUser getXUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
                   @RequestParam MultiValueMap<String, String> params);

    @PostMapping(value = "/2/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    XToken getXToken(@RequestParam MultiValueMap<String, String> params);
}
