package com.company.spring.tennis.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TennisTableWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisTableWsApplication.class, args);
	}

}
