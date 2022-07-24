package com.serasa.erestrito.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	private final long MAX_AGE_SECS = 3600;

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false) // que sempre especificará o formato padrão primeiro "json"
				.ignoreAcceptHeader(false) // não vai ignorar o formato especificado no cabeçalho da requisição
				.defaultContentType(MediaType.APPLICATION_JSON) // é o formato padrão
				.mediaType("json", MediaType.APPLICATION_JSON) // é o formato suportado pela API
				.mediaType("xml", MediaType.APPLICATION_XML);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
				.maxAge(MAX_AGE_SECS);
	}

}
