package com.project.cleanarchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@SpringBootApplication
@EnableSwagger2
public class CleanArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanArchitectureApplication.class, args);
	}

}
