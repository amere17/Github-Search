package com.example.ex3mohamadamer.demo;

import com.example.ex3mohamadamer.demo.bean.UserValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * main function
 */
@SpringBootApplication
public class DemoApplication {
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    /**
     * User Validation 
     */
    public UserValidation sessionB () {
        return new UserValidation();
    }
    public static void main(String[] args) {
        SpringApplication.run(com.example.ex3mohamadamer.demo.DemoApplication.class, args);
    }
}
