package org.hyeonjoon.gcu.global.config;

import graphql.schema.GraphQLScalarType;
import graphql.schema.Coercing;
import graphql.language.StringValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.scalars.ExtendedScalars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(localDateTimeScalar())
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.Json)
                .scalar(ExtendedScalars.LocalTime)
                .scalar(ExtendedScalars.Object)
                .scalar(ExtendedScalars.UUID)
                .scalar(ExtendedScalars.Url);
    }

    private GraphQLScalarType localDateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("Java LocalDateTime scalar")
                .coercing(new Coercing<LocalDateTime, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDateTime) {
                            return ((LocalDateTime) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                        return null;
                    }

                    @Override
                    public LocalDateTime parseValue(Object input) {
                        return LocalDateTime.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }

                    @Override
                    public LocalDateTime parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            return LocalDateTime.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                        return null;
                    }
                })
                .build();
    }
}