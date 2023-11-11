package com.se77.currencyConverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for general Spring beans.
 */
@Configuration
public class BeanConfig implements WebMvcConfigurer {

   

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}