package com.template.freelance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.apache.log4j.Logger;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class FreelanceDemoApplication {
	static Logger logger = Logger.getLogger(FreelanceDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FreelanceDemoApplication.class, args);
    	logger.info("Start Freelance Application Success");
	}

}
