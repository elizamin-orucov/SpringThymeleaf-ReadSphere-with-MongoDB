package com.read.sphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ReadSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadSphereApplication.class, args);
	}

}
