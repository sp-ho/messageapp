package com.example.messageapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://employee1-447012.ue.r.appspot.com/<endpoint>/<param>
// http://localhost:8081/swagger-ui.html
// http://localhost:8081/messageService/<endpoint>/<param>
@SpringBootApplication
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

}
