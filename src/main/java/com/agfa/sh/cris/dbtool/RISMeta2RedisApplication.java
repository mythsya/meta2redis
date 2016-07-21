package com.agfa.sh.cris.dbtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class RISMeta2RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RISMeta2RedisApplication.class, args);
	}
}
