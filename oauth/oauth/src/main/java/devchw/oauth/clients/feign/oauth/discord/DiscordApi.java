package devchw.oauth.clients.feign.oauth.discord;

import devchw.oauth.clients.feign.oauth.discord.dto.DiscordToken;
import devchw.oauth.clients.feign.oauth.discord.dto.DiscordUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "discord-api", url = "https://discord.com/api/v10")
public interface DiscordApi {

    @PostMapping(value = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, headers = {"Accept="})
    DiscordToken getDiscordToken(@RequestBody String tokenParam);

    @GetMapping(value = "/users/@me", consumes = MediaType.APPLICATION_JSON_VALUE)
    DiscordUser getDiscordUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

    @GetMapping(value = "/users/@me", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getImageUrlByHash(String avatar);

}
