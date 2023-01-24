/**
 * 
 */
package com.ibm.configuration;

import org.springframework.context.annotation.Bean;

import com.ibm.services.Customer;
import com.ibm.services.CustomerImpl;
import com.ibm.services.HelloWorld;
import com.ibm.services.HelloWorldImpl;

/**
 * @author 003OQT744
 *
 */
public class CustomerConfig {
	@Bean(name="customerBean")
    public Customer customer() {
        return new CustomerImpl();
    }
	private int custID;
	private String name;
	private String address;
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CustomerConfig(int custID, String name, String address) {
		super();
		this.custID = custID;
		this.name = name;
		this.address = address;
	}
	public CustomerConfig() {
		super();
	}
}
