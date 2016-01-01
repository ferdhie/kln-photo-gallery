package com.kapanlagi.web.photogallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ferdhie on 16-12-2015.
 */

@SpringBootApplication
public class PhotoGalleryWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PhotoGalleryWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PhotoGalleryWebApplication.class, args);
    }


}
