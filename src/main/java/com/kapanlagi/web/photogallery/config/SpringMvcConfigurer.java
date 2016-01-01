package com.kapanlagi.web.photogallery.config;

import com.kapanlagi.web.photogallery.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.util.List;

/**
 * Created by ferdhie on 23-12-2015.
 */
@Configuration
public class SpringMvcConfigurer extends WebMvcConfigurerAdapter {
    static final Logger LOG = LoggerFactory.getLogger(SpringMvcConfigurer.class);

    @Autowired
    FileStorageService storageService;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        File f = new File( storageService.getUploadDir() );

        String photoDir = new File(storageService.getImageDir()).getAbsolutePath() + "/";
        String thumbDir = new File(storageService.getThumbnailDir()).getAbsolutePath() + "/";

        LOG.info("photoDir={}, thumbDir={}", photoDir, thumbDir);

        registry.addResourceHandler("/photo/**").addResourceLocations("file:" + photoDir);
        registry.addResourceHandler("/thumb/**").addResourceLocations("file:" + thumbDir);
        registry.addResourceHandler("/README.md").addResourceLocations("file:README.md");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
