package com.config.client.configserverclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ConfigserverclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigserverclientApplication.class, args);
	}

}

