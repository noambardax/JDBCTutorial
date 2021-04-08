package com.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class DatabaseManager {
	private static ConnectionPull connPull = ConnectionPull.getInstance();
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

	private static final String QUERY_CREATE_COMPANIES = "CREATE TABLE `couponsystem`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";

	private static final String QUERY_DROP_COMPANIES = "DROP TABLE `couponsystem`.`companies`";

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

	private static ArrayList<String> initScripts;

	public DatabaseManager() {
		super();
		DatabaseManager.initScripts = new ArrayList<String>();

		DatabaseManager.initScripts.add(QUERY_CREATE_CATEGORIES);
		DatabaseManager.initScripts.add(QUERY_CREATE_COMPANIES);
		DatabaseManager.initScripts.add(QUERY_CREATE_COUPONS);
		DatabaseManager.initScripts.add(QUERY_CREATE_CUSTOMERS);
		DatabaseManager.initScripts.add(QUERT_CREATE_CUSTOMERS_VS_COUPONS);

	}

	public static void createSchemaCoupnsSystem() throws SQLException {

		try {
			connPull.executeQuery(QUERY_CREATE_SCHEMA);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void dropCouponSystemSchema() {
		try {
			connPull.executeQuery(QUERY_DROP_SCHEMA);

		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}
	}
	

	public static void createTableCategories() {

		try {
			connPull.executeQuery(QUERY_CREATE_CATEGORIES);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void dropTableCategories() {
		try {
			connPull.executeQuery(QUERY_DROP_CATEGORIES);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTableCompanies() {

		try {
			connPull.executeQuery(QUERY_CREATE_COMPANIES);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void dropTableCompanies() {
		try {
			connPull.executeQuery(QUERY_DROP_COMPANIES);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTableCoupons() {
		try {
			connPull.executeQuery(QUERY_CREATE_COUPONS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void dropTableCoupons() {
		try {
			connPull.executeQuery(QUERY_DROP_COUPONS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTableCustomers() {

		try {
			connPull.executeQuery(QUERY_CREATE_CUSTOMERS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void dropTableCustomers() {
		try {
			connPull.executeQuery(QUERY_DROP_CUSTOMERS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTableCustomerVsCoupons() {
		try {
			connPull.executeQuery(QUERT_CREATE_CUSTOMERS_VS_COUPONS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void dropTableCustomersVsCoupons() {
		try {
			connPull.executeQuery(QUERT_DROP_CUSTOMERS_VS_COUPONS);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void init() throws SQLException {
		/*
		 * createSchemaCoupnsSystem(); createTableCompanies(); createTableCustomers();
		 * createTableCategories(); createTableCoupons();
		 * createTableCustomerVsCoupons(); // connPull.executeQuery();
		 */
		try {

			for (String initScript : initScripts) {
				connPull.executeQuery(initScript);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
