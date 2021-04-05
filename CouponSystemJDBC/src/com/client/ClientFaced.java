package com.client;

import com.Db.CompanyDao;
import com.Db.CouponDao;
import com.Db.CustomerDao;

public abstract class ClientFaced {
     CompanyDao companyDao;
     CustomerDao customerDao;
     CouponDao couponDao;
     
     public static boolean logIn(String email, String password) {
		return false;
    	 
     }
}
