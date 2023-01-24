/**
 * 
 */
package com.ibm.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ibm.services.HelloWorld;
import com.ibm.services.HelloWorldImpl;

/**
 * @author 003OQT744
 *
 */
@Configuration
public class HelloWorldConfig {
	@Bean(name="helloBean")
    public HelloWorld helloWorld() {
        return new HelloWorldImpl();
    }

}
