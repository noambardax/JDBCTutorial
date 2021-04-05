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

public class CouponDBDAO implements CouponDao {
	private static final String QUERY_INSERT = "INSERT INTO `couponsystem`.`coupons` (`id`, `company_Id`, `title`, `description`,`start_Date`, `end_Date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ? ,? , ?, ? , ?);";
	private static final String QUERY_UPDATE = "UPDATE `couponsystem`.`coupons` SET `id` = ?, `companyId` = ?, `title` = ? ,`description` = ? `startDate` = ? ,`endDate` = ? ,`amount` = ? ,`price` = ? ,`image` = ? WHERE (`id` = ?);\r\n";
	private static final String QUERY_DELETE = "DELETE FROM `couponsystem`.`coupons` WHERE (`id` = ?);";
	private static final String QUERY_GET_ONE = "SELECT * FROM `couponsystem`.`coupons` WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL = "SELECT * FROM `couponsystem`.`coupons`";
	private static final String QUERY_INSERT_PURCHES = "INSERT INTO `couponsystem`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUSE (?,?);";
	@Override
	public void addCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1, coupon.getId());
			statement.setInt(2, coupon.getCompanyId());
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, coupon.getStartDate());
			statement.setDate(6, coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImageString());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
	public void updateCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setInt(1, coupon.getId());
			statement.setInt(2, coupon.getCompanyId());
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, coupon.getStartDate());
			statement.setDate(6, coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImageString());
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
	public void deleteCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, coupon.getId());
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
	public Coupon getOneCoupon(int couponID) {

		Coupon result = null;

		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, couponID);

			// STEP 4 - Optional only for reuslts
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = new Coupon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getInt(7),
					resultSet.getDouble(8), resultSet.getString(9));
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
    public List<Coupon> getAllCoupons(Coupon coupon) {
		List<Coupon> coupons = new ArrayList<>();

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
				Coupon coupon1 = new Coupon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getInt(7),
						resultSet.getDouble(8), resultSet.getString(9));
				coupons.add(coupon1);
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

		return coupons;
	}

	
	@Override
	public void addCouponPurches(int customerId, int couponId) {
		Connection conn = null;
		try {
			// STEP 2 - Open Connection
			conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
					DatabaseManager.getPass());
			// STEP 3 - Run Script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1, customerId);
			statement.setInt(2, couponId);
			
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

}