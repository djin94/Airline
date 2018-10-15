package com.foxminded.airline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.foxminded.airline")
public class WebMVCConfig implements WebMvcConfigurer {
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/static/css/");
        registry.addResourceHandler( "/js/**").addResourceLocations("/js/");
        registry.addResourceHandler( "/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/user/js/**").addResourceLocations("/user/js/");
        registry.setOrder(-1);
//        registry.addResourceHandler("/resources/**","/static/**","/css/**", "/js/**", "/images/**","/**","/user/js/**").addResourceLocations("/resources/");
    }
}
