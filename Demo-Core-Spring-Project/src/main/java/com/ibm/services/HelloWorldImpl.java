/**
 * 
 */
package com.ibm.services;

import org.springframework.stereotype.Service;

/**
 * @author 003OQT744
 *
 */
@Service
public class HelloWorldImpl implements HelloWorld{
	public String printHelloWorld(String msg) {

		System.out.println("Hello : " + msg);
		
		return "Hello" + msg;
	}


}
