package com.example.stockautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StockAutomationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(StockAutomationApplication.class,args);
		SpringApplication.exit(run);
	}

}
