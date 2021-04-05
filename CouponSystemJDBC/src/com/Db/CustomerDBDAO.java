package com.Db;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;


public class CustomerDBDAO implements CustomerDao{
	private static final String QUERY_INSERT = "INSERT INTO `couponsystem`.`customers` (`id`, `firstName`, `lastName`, email`, `password`,) VALUES (?, ?, ?, ?,?,);";
	private static final String QUERY_UPDATE = "UPDATE `couponsystem`.`customers` SET `firstName` = ?, `lastName` = ?, `email` = ? ,`password` = ? `coupons` = ? WHERE (`id` = ?);\r\n";
	private static final String QUERY_DELETE = "DELETE FROM `couponsystem`.`customers` WHERE (`id` = ?);";
	private static final String QUERY_GET_ONE = "SELECT * FROM `couponsystem`.`customers` WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL = "SELECT * FROM `couponsystem`.`customers`";
	@Override
	public void addCustomer(Customer customer) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1, customer.getId());
			statement.setString(2, customer.getFirsName());
			statement.setString(3, customer.getLastName());
			statement.setString(4, customer.getEmail());
			statement.setString(5, customer.getPassword());
			//statement.setArray(6, (Array) customer.getCoupons());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

	public void updateCustomer(Customer customer) {

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setInt(1, customer.getId());
			statement.setString(2, customer.getFirsName());
			statement.setString(3, customer.getLastName());
			statement.setString(4, customer.getEmail());
			statement.setString(5, customer.getPassword());
			statement.setArray(6, (Array) customer.getCoupons());
			statement.executeUpdate();
			// STEP 4 - Optional only for reuslts
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, customer.getId());
			statement.executeUpdate();
			// STEP 4 - Optional only for reuslts
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Customer getOneCustomer(int customerID) {
		 Customer result = null;
			
			
			Connection conn = null;
			try {
				// STEP 2 - Open Connection
				conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
						DatabaseManager.getPass());
				// STEP 3 - Run Script
				PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
				statement.setInt(1, customerID);
				
				// STEP 4 - Optional only for reuslts
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				result = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), (ArrayList) resultSet.getArray(6));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				// STEP 5 - Close

				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return result;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);

			// STEP 4 - Optional only for reuslts
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						 resultSet.getString(4), resultSet.getString(5),(ArrayList) resultSet.getArray(6));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return customers;
		
	}

	@Override
	public boolean isCustomerExist(Customer customer) {
		List<Customer> customers = new ArrayList<>();

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);

			// STEP 4 - Optional only for reuslts
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Customer c1 = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						 resultSet.getString(4), resultSet.getString(5),(ArrayList) resultSet.getArray(6));
				customers.add(c1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

       if (customers.contains(customer)) {
		return true;
	}		 
		
			
		
		return false;
	}

	

	
	
	
}
