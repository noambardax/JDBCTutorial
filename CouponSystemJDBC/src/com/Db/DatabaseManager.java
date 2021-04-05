package com.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DatabaseManager {

	private static final String URL = "jdbc:mysql://localhost:3306/couponsystem?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";

	private static final String USER = "root";

	private static final String PASS = "1234";

	private static final String QUERY_CREATE_SCHEMA = "CREATE SCHEMA `couponsystem` ;";
	
	private static final String QUERY_DROP_SCHEMA = "DROP SCHEMA `couponsystem`;";
	
    private static final String QUERY_CREATE_CATEGORIES = "CREATE TABLE `couponsystem`.`categories` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	
    private static final String QUERY_DROP_CATEGORIES = "DROP TABLE `couponsystem`.`categories`";

	private static final String QUERY_CREATE_CUSTOMERS = "CREATE TABLE `couponsystem`.`customers` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_nam` VARCHAR(45) NOT NULL,\r\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `password` VARCHAR(45) NULL,\r\n" + "  PRIMARY KEY (`id`));";
	
	private static final String QUERY_DROP_CUSTOMERS = "DROP TABLE `couponsystem`.`customers`";

	private static final String QUERY_CREATE_COMPANY = "CREATE TABLE `couponsystem`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";

	private static final String QUERY_DROP_COMPANY = "DROP TABLE `couponsystem`.`companies`";

	private static final String QUERY_CREATE_COUPONS = "CREATE TABLE `couponsystem`.`coupons` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` INT NOT NULL,\r\n"
			+ "  `category_id` INT NOT NULL,\r\n" + "  `title` VARCHAR(45) NOT NULL,\r\n"
			+ "  `description` VARCHAR(45) NOT NULL,\r\n" + "  `start_date` DATE NOT NULL,\r\n"
			+ "  `end_date` DATE NOT NULL,\r\n" + "  `amount` INT NULL,\r\n" + "  `price` DOUBLE NOT NULL,\r\n"
			+ "  `image` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\r\n"
			+ "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `company_id`\r\n"
			+ "    FOREIGN KEY (`company_id`)\r\n" + "    REFERENCES `couponsystem`.`companies` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `category_id`\r\n"
			+ "    FOREIGN KEY (`category_id`)\r\n" + "    REFERENCES `couponsystem`.`categories` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);\r\n" + "";
	
	private static final String QUERY_DROP_COUPONS = "DROP TABLE `couponsystem`.`coupons`";

	private static final String QUERT_CREATE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `couponsystem`.`customers_vs_coupons` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n"
			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `customer_id`\r\n"
			+ "    FOREIGN KEY (`customer_id`)\r\n" + "    REFERENCES `couponsystem`.`customers` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `coupon_id`\r\n"
			+ "    FOREIGN KEY (`coupon_id`)\r\n" + "    REFERENCES `couponsystem`.`coupons` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);\r\n" + "";
	
	private static final String QUERT_DROP_CUSTOMERS_VS_COUPONS = "DROP TABLE `couponsystem`.`customers_vs_coupons`";

	public static void createSchemaCoupnsSystem() throws SQLException {
		ConnectionPull conn = null;
		try {
			// STEP 2 - Open Connection
			conn = ConnectionPull.getInstance();
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_CREATE_SCHEMA);
			System.out.println("statment was made");
			statement.executeUpdate();
			System.out.println("schema was created");
			// STEP 4 - Optional only for reuslts
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close

			conn.close();
		}
	}

	public static void dropCouponSystemSchema() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DROP_SCHEMA);
			statement.executeUpdate();
			System.out.println("schema was deleted");
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

	public static void createTableCategories() {

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_CREATE_CATEGORIES);
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

	public static void dropTableCategories() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DROP_CATEGORIES);
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

	public static void createTableCompanies() {

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_CREATE_COMPANY);
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

	public static void dropTableCompanies() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DROP_COMPANY);
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

	public static void createTableCoupons() {

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_CREATE_COUPONS);
			statement.executeUpdate();
			System.out.println("table coupons was created");
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

	public static void dropTableCoupons() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DROP_COUPONS);
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

	public static void createTableCustomers() {

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_CREATE_CUSTOMERS);
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

	public static void dropTableCustomers() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DROP_CUSTOMERS);
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

	public static void createTableCustomerVsCoupons() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERT_CREATE_CUSTOMERS_VS_COUPONS);
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

	public static void dropTableCustomersVsCoupons() {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERT_DROP_CUSTOMERS_VS_COUPONS);
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

	public static void init() throws SQLException {
		createSchemaCoupnsSystem();
		createTableCompanies();
		createTableCustomers();
		createTableCategories();
		createTableCoupons();
		createTableCustomerVsCoupons();
	}

	public static void terminate() {
		dropTableCustomersVsCoupons();
		dropTableCoupons();
		dropTableCategories();
		dropTableCustomers();
		dropTableCompanies();
		dropCouponSystemSchema();
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPass() {
		return PASS;
	}

}
