package net.codeJava.atozEntity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class AtozEntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtozEntityApplication.class, args);
	}

}
