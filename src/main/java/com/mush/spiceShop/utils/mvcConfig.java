package com.mush.spiceShop.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class mvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path imageUploadDir = Paths.get("./productImg");
		String imageUploadPath = imageUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/productImg/**").addResourceLocations("file:/" + imageUploadPath +"/");
	}

}
