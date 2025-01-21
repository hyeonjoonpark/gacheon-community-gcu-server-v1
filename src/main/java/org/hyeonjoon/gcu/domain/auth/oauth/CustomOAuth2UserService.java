package org.hyeonjoon.gcu.domain.auth.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hyeonjoon.gcu.domain.user.Users;
import org.hyeonjoon.gcu.domain.user.enums.Role;
import org.hyeonjoon.gcu.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        log.info("oAuth2User: {}", oAuth2User);

        // 사용자 정보 가져오기
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 사용자 정보 처리
        String email = (String) attributes.get("email");
        String username = (String) attributes.get("name");
        String id = (String) attributes.get("sub"); // OAuth2 사용자 ID

        // 데이터베이스에서 사용자 조회
        boolean isUserExists = userRepository.existsByUsername(username);

        // 사용자 정보가 없으면 새로 저장
        if (!isUserExists) {
            Users user = Users.builder()
                    .id(id)
                    .username(username)
                    .email(email)
                    .password("")
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(user);
            log.info("새 사용자 저장: {}", user);
        }

        // DefaultOAuth2User 반환
        return new DefaultOAuth2User(
                Collections.singleton(new OAuth2UserAuthority(attributes)),
                attributes,
                "email"
        );
    }
}
