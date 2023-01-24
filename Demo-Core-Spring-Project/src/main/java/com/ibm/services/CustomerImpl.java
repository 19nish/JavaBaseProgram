/**
 * 
 */
package com.ibm.services;

import java.util.ArrayList;
import java.util.List;

import com.ibm.configuration.CustomerConfig;

/**
 * @author 003OQT744
 *
 */
public class CustomerImpl implements Customer {
	List<CustomerConfig> Customer= new ArrayList<CustomerConfig>();
	public void createCustomer() {
	CustomerConfig c1 = new CustomerConfig(1, "Nish", "Roorkie");
	CustomerConfig c2= new CustomerConfig(2, "Shubh", "Roorkie");
	CustomerConfig c3 = new CustomerConfig(3, "Ravi", "Roorkie");
	CustomerConfig c4 = new CustomerConfig(4, "Kiru", "Roorkie");
	Customer.add(c1);
	Customer.add(c2);
	Customer.add(c3);
	Customer.add(c4);
	}
}
 