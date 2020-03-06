package com.github.binarywang.demo.wx.miniapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Jason
 * @title: WebMvcConfig
 * @description: WebMvcConfig
 * @date 2020/3/6  15:37
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        registry.addResourceHandler(("/image/**"))
            .addResourceLocations("file:" + uploadFolder);

    }
}
