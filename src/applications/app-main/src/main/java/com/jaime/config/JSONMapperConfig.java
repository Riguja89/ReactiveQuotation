package com.jaime.config;

import com.jaime.serializer.JSONMapper;
import com.jaime.serializer.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONMapperConfig {

    @Bean
    public JSONMapper jsonMapper() {
        return new JSONMapperImpl();
    }
}
