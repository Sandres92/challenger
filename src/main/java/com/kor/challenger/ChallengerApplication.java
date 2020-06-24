package com.kor.challenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@EnableAuthorizationServer
@SpringBootApplication
//@EnableResourceServer
public class ChallengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengerApplication.class, args);
	}

}
