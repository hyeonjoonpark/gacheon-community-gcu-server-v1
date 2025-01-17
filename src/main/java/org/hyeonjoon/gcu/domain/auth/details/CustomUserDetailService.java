package org.hyeonjoon.gcu.domain.auth.details;

import lombok.RequiredArgsConstructor;
import org.hyeonjoon.gcu.domain.user.repository.UserRepository;
import org.hyeonjoon.gcu.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + ": " + "찾을 수 없는 사용자입니다");
        }
        return new CustomUserDetails(user, new HashMap<>());
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id + ": " + "찾을 수 없는 사용자입니다"));
        return new CustomUserDetails(user, new HashMap<>());
    }
}
