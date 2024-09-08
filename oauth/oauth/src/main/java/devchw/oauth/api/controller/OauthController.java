package devchw.oauth.api.controller;

import devchw.oauth.api.controller.dto.response.OAuthLoginTokenResponse;
import devchw.oauth.api.domain.OauthService;
import devchw.oauth.global.enums.OauthServiceType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauth2Facade;

    /**
     * Oauth 로그인
     */
    @GetMapping("/api/oauth/{oauthServiceType}/login")
    public void loginOrSignUp(
            @PathVariable("oauthServiceType") OauthServiceType oAuthServiceType,
            @RequestParam("code") String code,
            @RequestParam("redirectUri") String redirectUri,
            HttpServletResponse response) throws IOException {
        OAuthLoginTokenResponse data = oauth2Facade.loginOrSignUp(code, oAuthServiceType, redirectUri);
        response.addHeader(HttpHeaders.AUTHORIZATION, data.accessToken());
        response.sendRedirect("hello");
    }

}
