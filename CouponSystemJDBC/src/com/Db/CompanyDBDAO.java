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

public class CompanyDBDAO implements CompanyDao {
	private static final String QUERY_INSERT = "INSERT INTO `couponsystem`.`companies` (`id`, `name`,`email`, `password`) VALUES (?, ?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `couponsystem`.`companies` SET `name` = ?, `email` = ?, `password` = ? , `coupons` = ? WHERE (`id` = ?);\r\n";
	private static final String QUERY_DELETE = "DELETE FROM `couponsystem`.`companies` WHERE (`id` = ?);";
	private static final String QUERY_GET_ONE = "SELECT * FROM `couponsystem`.`companies` WHERE (`id` = ?);";
	private static final String QUERY_GEL_ALL = "SELECT * FROM `couponsystem`.`companies`";
	
	@Override
	public void addCompany(Company company) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1, company.getId());
			statement.setString(2, company.getName());
			statement.setString(3, company.getEmail());
			statement.setString(4, company.getPassword());
			//statement.setArray(5, (Array) company.getCoupons());
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

	@Override
	public void updateCompany(Company company) {

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());
			statement.setArray(5, (Array) company.getCoupons());
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

	public void deleteCompany(Company company) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, company.getId());
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
	public Company getOneCompany(int companyID) {
		Company result = null;

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, companyID);

			// STEP 4 - Optional only for reuslts
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), (ArrayList) resultSet.getArray(5));
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
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<>();

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_GEL_ALL);

			// STEP 4 - Optional only for reuslts
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4);
				companies.add(company);
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

		return companies;
	}

	
	public boolean isCompanyExist(Company company) {
		List<Company> companies = new ArrayList<>();

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_GEL_ALL);

			// STEP 4 - Optional only for reuslts
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company1 = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
				return true;
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

      
		return false;
	}
}