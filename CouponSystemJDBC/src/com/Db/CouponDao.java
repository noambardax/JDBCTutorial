package com.Db;

import java.util.List;

import com.beans.Coupon;

public interface CouponDao {
	
	void addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	void deleteCoupon(Coupon coupon);

	Coupon getOneCoupon(int couponID);

	List<Coupon> getAllCoupons(Coupon coupon);

	void addCouponPurches(int customerId , int couponId);

	
}