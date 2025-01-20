package org.hyeonjoon.gcu.global.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //authentication에 OAuth 토큰 발급 후 전달 받은 사용자 정보가 들어있다.
        //토큰 발급 요청 부터 사용자 정보 요청까지 Spring Security가 내부적으로 진행한다.

        //사용자 정보 추출
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        //사용자 정보를 전달 또는 JWT 생성 후 전달
        //일반적으로는 사용자 정보를 바탕으로 JWT를 생성하고 전달한다.
        Cookie cookie = new Cookie("name", (String) attributes.get("name"));
        cookie.setPath("/");
        response.addCookie(cookie);

        String targetUrl = "http://localhost:3000"; //redirect 시킬 react 경로
        String redirectUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
