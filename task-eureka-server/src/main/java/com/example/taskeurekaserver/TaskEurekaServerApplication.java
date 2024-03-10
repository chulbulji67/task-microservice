package com.example.taskeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskEurekaServerApplication.class, args);
	}

}
