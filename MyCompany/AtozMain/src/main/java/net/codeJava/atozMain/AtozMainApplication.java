package net.codeJava.atozMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = 
{"net.codeJava.*"})
@EnableAutoConfiguration
public class AtozMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtozMainApplication.class, args);
	}

}
