package org.paolino.sb2026.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.DeserializationFeature;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public JsonMapperBuilderCustomizer customizer() {
        return builder -> builder
                .disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
    }
}
