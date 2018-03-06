package com.zimug.imooc.websky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"net.aexit.galaxy","com.zimug.imooc"})
public class WebskyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebskyApplication.class, args);
	}
}
