package org.hyeonjoon.gcu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public String test() {
        return "이 글씨가 보인다면 성공적인 서버 배포";
    }
}
