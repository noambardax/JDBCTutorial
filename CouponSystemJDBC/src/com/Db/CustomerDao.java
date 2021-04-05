package com.Db;

import java.util.List;

import com.beans.Coupon;
import com.beans.Customer;

public interface CustomerDao {
	
	boolean isCustomerExist(Customer customers);

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Customer customer);

	Customer getOneCustomer(int customerID);

	List<Customer> getAllCustomers();

}
