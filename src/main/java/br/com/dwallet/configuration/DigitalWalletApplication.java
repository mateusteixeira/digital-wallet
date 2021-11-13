package br.com.dwallet.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "br.com.dwallet.model")
@PropertySource("classpath:application.properties")
@ComponentScan("br.com.dwallet")
public class DigitalWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalWalletApplication.class, args);
	}

}
