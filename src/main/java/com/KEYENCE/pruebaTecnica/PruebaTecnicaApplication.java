package com.KEYENCE.pruebaTecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.KEYENCE.pruebaTecnica.properties.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({ 
	FileUploadProperties.class
})
public class PruebaTecnicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

}
