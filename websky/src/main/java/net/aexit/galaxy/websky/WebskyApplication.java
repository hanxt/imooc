package net.aexit.galaxy.websky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"net.aexit.galaxy"})
public class WebskyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebskyApplication.class, args);
	}
}
