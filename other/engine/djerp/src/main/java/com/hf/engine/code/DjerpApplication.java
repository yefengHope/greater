package com.hf.engine.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:test.properties")
public class DjerpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DjerpApplication.class, args);
	}
}
