package org.hyeonjoon.gcu.global.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return
                wiringBuilder -> wiringBuilder
                        .scalar(ExtendedScalars.DateTime)
                        .scalar(ExtendedScalars.Date)
                        .scalar(ExtendedScalars.Json)
                        .scalar(ExtendedScalars.LocalTime)
                        .scalar(ExtendedScalars.Object)
                        .scalar(ExtendedScalars.UUID)
                        .scalar(ExtendedScalars.Url);
    }
}