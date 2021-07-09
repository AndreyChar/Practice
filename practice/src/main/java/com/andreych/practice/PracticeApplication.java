package com.andreych.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = "com.andreych")
public class PracticeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(PracticeApplication.class, args);
	}

}
