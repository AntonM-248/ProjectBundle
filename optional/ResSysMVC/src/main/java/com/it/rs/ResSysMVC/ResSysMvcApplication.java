package com.it.rs.ResSysMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:configuration.properties" })
public class ResSysMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResSysMvcApplication.class, args);
	}

}
