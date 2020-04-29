package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Boot1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Boot1Application.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(Boot1Application.class);
	}

}
@RestController
class controllers{
	@RequestMapping("/hi")
	public String sayhello() {
		return "hello world!";
	}
	
}
