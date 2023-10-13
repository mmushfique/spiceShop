package com.mush.spiceShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpiceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiceShopApplication.class, args);
	}

}
