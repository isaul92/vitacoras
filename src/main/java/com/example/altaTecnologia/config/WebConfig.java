package com.example.altaTecnologia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// inyectando la constante de application properties
	@Value("${altaTecnologia.ruta.informacionFiscal}")
	private String rutaInformacion;
	@Value("${altaTecnologia.ruta.imagenes}")
	private String rutaImagenes;

	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/");
		// // Linux
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes); // Windows
		registry.addResourceHandler("/fiscal/**").addResourceLocations("file:" + rutaInformacion);

	}

}
