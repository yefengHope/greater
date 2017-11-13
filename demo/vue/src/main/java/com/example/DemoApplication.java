package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

		for (int i=0; i<10;i++) {
			sOut(i);
		}

	}

	public static void sOut (Object i) {
		System.out.println(i);
	}
}
