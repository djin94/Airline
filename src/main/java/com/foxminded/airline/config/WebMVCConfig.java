package com.foxminded.airline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

//@Configuration
//@EnableWebMvc
//@ComponentScan("com.foxminded.airline")
public class WebMVCConfig  {

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//        registry.addResourceHandler( "/js/**").addResourceLocations("/js/");
//        registry.addResourceHandler( "/images/**").addResourceLocations("/images/");
//        registry.addResourceHandler("/user/js/**").addResourceLocations("/user/js/");
//        registry.addResourceHandler("/webjars**").addResourceLocations("classpath:META-INF/resources/webjars/");
//        registry.setOrder(-1);
//        registry.addResourceHandler("/resources/**","/static/**","/css/**", "/js/**", "/images/**","/**","/user/js/**").addResourceLocations("/resources/");
    }

//    @Override
//    public void configureContentNegotiation (ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.TEXT_HTML);
//    }
}