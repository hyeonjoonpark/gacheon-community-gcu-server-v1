package org.hyeonjoon.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GcuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcuApplication.class, args);
    }

}
