package devchw.oauth.api.presentation.controller;

import devchw.oauth.api.presentation.controller.dto.response.OAuthConnectResponse;
import devchw.oauth.api.presentation.controller.dto.response.OAuthLoginPageUrlResponse;
import devchw.oauth.api.presentation.controller.dto.response.OAuthLoginResponse;
import devchw.oauth.api.presentation.facade.OAuthFacade;
import devchw.oauth.enums.OAuthServerType;
import devchw.oauth.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthFacade oauth2Facade;

    /**
     * OAuth2 로그인 페이지 URL 조회
     */
    @GetMapping("/oauth/{oauthServerType}/login")
    public ApiResponse<OAuthLoginPageUrlResponse> getOAuthLoginPageUrl(
            @PathVariable("oauthServerType") OAuthServerType oauthServerType) {
        OAuthLoginPageUrlResponse data = oauth2Facade.getOAuthLoginPageUrl(oauthServerType);
        return ApiResponse.success(data);
    }

    /**
     * OAuth2 로그인
     */
    @PostMapping("/oauth/{oauthServerType}/login")
    public ApiResponse<OAuthLoginResponse> login(
            @PathVariable("oauthServerType") OAuthServerType oauthServerType,
            @RequestBody String code) {
        OAuthLoginResponse data = oauth2Facade.login(code, oauthServerType);
        return ApiResponse.success(data);
    }

    /**
     * OAuth2 소셜 계정 연결
     */
    @PatchMapping("/oauth/{oauthServerType}/connect")
    public ApiResponse<OAuthConnectResponse> connectSocialAccount(
            @PathVariable("oauthServerType") OAuthServerType oauthServerType,
            @RequestBody String code) {
        OAuthConnectResponse data = oauth2Facade.connectOAuthAccount(code, oauthServerType);
        return ApiResponse.success(data);
    }

    /**
     * 정상동작 확인용 RedirectUrl
     */
    @GetMapping("/oauth/{oauthServerType}/test")
    public ResponseEntity<Void> codeCheckTest(
            @PathVariable("oauthServerType") OAuthServerType oAuthServerType,
            @RequestParam("code") String code,
            @RequestParam("state") String state) {
        System.out.println("code = " + code);
        System.out.println("state = " + state);
        OAuthLoginResponse login = oauth2Facade.login(code, oAuthServerType);
        System.out.println("login.accessToken() = " + login.accessToken());
        return ResponseEntity.ok().build();
    }

}
