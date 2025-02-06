package org.hyeonjoon.gcu.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.hyeonjoon.gcu.domain.auth.SignUpRequest;
import org.hyeonjoon.gcu.domain.user.Users;
import org.hyeonjoon.gcu.domain.user.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @MutationMapping(name = "signUp")
    public void signUp(@Argument SignUpRequest signUpRequest, @AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요한 서비스입니다.");
        }

        if (signUpRequest == null) {
            throw new IllegalArgumentException("회원 가입 요청이 유효하지 않습니다.");
        }

        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");

        Optional<Users> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            logger.info("기존 사용자 정보: {}", user);
            user.update(signUpRequest.department(), signUpRequest.enteredYear(), signUpRequest.name());
            userRepository.save(user);
            logger.info("사용자 정보 업데이트 완료");
        } else {
            Users newUser = Users.builder()
                    .email(email)
                    .department(signUpRequest.department())
                    .enteredYear(signUpRequest.enteredYear())
                    .username(signUpRequest.name())
                    .build();
            userRepository.save(newUser);
            logger.info("새 사용자 등록 완료: {}", newUser);
        }
    }
}
