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
        log.info("oauth attributes: {}", oAuth2User);

        // 사용자 이름 추출
        String id = (String) attributes.get("sub");

        if (!id.isEmpty()) { // 빈 문자열 체크
            // 쿠키 값 검증

            // 쿠키 생성
            Cookie cookie = new Cookie("id", id);
            cookie.setPath("/");
            cookie.setHttpOnly(true); // JavaScript에서 접근 불가
            response.addCookie(cookie);

        } else {
            // 유효하지 않은 쿠키 값 처리
            System.out.println("Invalid cookie value for userName.");
        }

        boolean isUserExist = userRepository.findByUsernameIsNull();
        log.info("isUserExist: {}", isUserExist);
        // 리다이렉트 URL 설정
        String targetUrl = isUserExist ? "http://localhost:3001" : "http://localhost:3001/signup"; // 리다이렉트 시킬 React 경로
        String redirectUrl = UriComponentsBuilder
                .fromUriString(targetUrl)
                .build()
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
