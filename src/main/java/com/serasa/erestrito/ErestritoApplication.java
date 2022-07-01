package com.serasa.erestrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.serasa.erestrito.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
public class ErestritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErestritoApplication.class, args);
	}

}
