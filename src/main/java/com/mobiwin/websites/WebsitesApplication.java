package com.mobiwin.websites;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${upload.path}")
    private String uploadPath;

	public static void main(String[] args) {
		SpringApplication.run(WebsitesApplication.class, args);
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
            "/**",
            "/front/**",
            "/back/**",
            "/upload/**",
            "/carousel/**",
            "/team/**",
            "/project/**",
            "/testimony/**",
            "/client/**",
            "/career/**"
        )
        .addResourceLocations(
            "classpath:/static/",
            "classpath:/static/front/",
            "classpath:/static/back/",
            // "classpath:/static/upload/carousel/",
            // "classpath:/static/upload/team/",
            // "classpath:/static/upload/project/",
            // "classpath:/static/upload/testimony/",
            // "classpath:/static/upload/client/",
            // "classpath:/static/upload/career/",
            "classpath:/upload/carousel/",
            "classpath:/upload/team/",
            "classpath:/upload/project/",
            "classpath:/upload/testimony/",
            "classpath:/upload/client/",
            "classpath:/upload/career/",
            "file:"+ uploadPath
        );
    }

}
