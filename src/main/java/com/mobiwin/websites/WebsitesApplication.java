package com.mobiwin.websites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@SpringBootApplication
public class WebsitesApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WebsitesApplication.class, args);
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
            "/**",
            "/front/**",
            "/back/**",
            "/upload/**"
        )
        .addResourceLocations(
            "classpath:/static/",
            "classpath:/static/front/",
            "classpath:/static/back/",
            "classpath:/static/upload/"
        );
    }

}
