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
				"/vendor/**",
				"/img/**",
                "/css/**",
                "/js/**",
                "/css/**",
                "/js/plugins/**",
                "/img/**",
                "/fonts/**",
                "/ckeditor/**"
                )
                .addResourceLocations(
						"classpath:/static/front/vendor/",
                        "classpath:/static/front/img/",
                        "classpath:/static/front/css/",
                        "classpath:/static/front/js/",
                        "classpath:/static/back/css/",
                        "classpath:/static/back/js/",
                        "classpath:/static/back/js/plugins/",
                        "classpath:/static/back/img/",
                        "classpath:/static/back/fonts/",
                        "classpath:/static/back/ckeditor/",
                        "classpath:/static/back/upload/**"
                        );
    }

}
