package org.hyeonjoon.gcu.global.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hyeonjoon.gcu.domain.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // OAuth2 사용자 정보 추출
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        log.info("oauth attributes: {}", attributes);

        // 사용자 이름 추출 및 공백 제거
        String userName = (String) attributes.get("name");
        userName = userName.replace(" ", "_"); // 공백 제거
        log.info("userName: {}", userName);

        if (!userName.isEmpty()) { // 빈 문자열 체크
            // 쿠키 값 검증
            if (isValidCookieValue(userName)) {
                // 쿠키 생성
                Cookie cookie = new Cookie("name", userName);
                cookie.setPath("/");
                cookie.setHttpOnly(true); // JavaScript에서 접근 불가
                response.addCookie(cookie);
            } else {
                // 유효하지 않은 쿠키 값 처리
                System.out.println("Invalid cookie value for userName.");
            }
        } else {
            // 유효하지 않은 쿠키 값 처리
            System.out.println("Invalid cookie value for userName.");
        }

        boolean isUserExist = userRepository.existsByUsername(userName);
        // 리다이렉트 URL 설정
        String targetUrl = isUserExist ? "http://localhost:3001" : "http://localhost:3001/signup"; // 리다이렉트 시킬 React 경로
        String redirectUrl = UriComponentsBuilder
                .fromUriString(targetUrl)
                .build()
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private boolean isValidCookieValue(String value) {
        // 쿠키 값이 유효한지 검사하는 로직 (예: 공백, 특수문자 체크)
        return !value.contains(" ") && value.matches("^[\\w\\-\\.]+$"); // 알파벳, 숫자, -, . 만 허용
    }
}
