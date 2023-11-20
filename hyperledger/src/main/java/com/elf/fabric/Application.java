package com.elf.fabric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
public class Application  extends SpringBootServletInitializer implements WebApplicationInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
