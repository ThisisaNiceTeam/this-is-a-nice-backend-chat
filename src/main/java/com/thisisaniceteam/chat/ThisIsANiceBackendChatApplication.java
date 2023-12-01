package com.thisisaniceteam.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ThisIsANiceBackendChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThisIsANiceBackendChatApplication.class, args);
	}

}
