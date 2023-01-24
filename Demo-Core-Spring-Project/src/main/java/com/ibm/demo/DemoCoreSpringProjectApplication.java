package com.ibm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ibm.configuration.ApplicationConfig;
import com.ibm.configuration.HelloWorldConfig;
import com.ibm.services.Customer;
import com.ibm.services.HelloWorld;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@Import((HelloWorldConfig.class))
public class DemoCoreSpringProjectApplication {

	public static void main(String[] args) {
		/*
		 * SpringApplication.run(DemoCoreSpringProjectApplication.class, args);
		 * System.out.println("This is my first Spring Application");
		 */
ApplicationContext context=SpringApplication.run(ApplicationConfig.class);
		
	    HelloWorld obj = (HelloWorld) context.getBean("helloBean");
	    obj.printHelloWorld("World");
	    Customer cus=(Customer) context.getBean("customerBean");
	    cus.createCustomer();
		}

}
