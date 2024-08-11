package devchw.oauth.security;

import devchw.oauth.security.jwt.JwtAuthenticationFilter;
import devchw.oauth.security.jwt.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter tokenAuthenticationFilter;
    private final JwtExceptionFilter tokenExceptionFilter;
    private static final String[] WHITE_LIST = {"/health", "/docs", "/error", "/favicon.ico"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 기본 설정
                .cors(cors -> corsConfigurationSource()) // cors 설정
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
                .httpBasic(HttpBasicConfigurer::disable) // 기본 인증 로그인 비활성화
                .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
                .headers(c -> c.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable).disable()) // X-Frame-Options 비활성화
                .sessionManagement((sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))) // 세션 사용 X

                // request 인증, 인가 설정
                .authorizeHttpRequests(request ->
                        request.requestMatchers(WHITE_LIST).permitAll()
                                .anyRequest().permitAll())

                // jwt 필터 관련 설정
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenExceptionFilter, tokenAuthenticationFilter.getClass()); // 토큰 예외 핸들링

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var corsConfig = new CorsConfiguration();
        corsConfig.setAllowedMethods(List.of("*"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setAllowCredentials(true);

        var corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }

}
