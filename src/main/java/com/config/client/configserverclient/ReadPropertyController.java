package com.config.client.configserverclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ReadPropertyController {
	@Value("${spring.datasource.url: url not found}")
	private String configMessage;
	
	@GetMapping("/")
	public String welcome() {
		return "welcome shridhar";
	}
	@GetMapping("/${server.contextPath}")
	public String jdbcUrl() {
		return configMessage;
	}

}
