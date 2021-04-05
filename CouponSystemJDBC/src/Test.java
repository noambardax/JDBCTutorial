import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.Db.CompanyDBDAO;
import com.Db.ConnectionPull;
import com.Db.CouponDBDAO;
import com.Db.CustomerDBDAO;
import com.Db.DatabaseManager;
import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException {
		System.out.println("START");
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		long time = System.currentTimeMillis();
		java.sql.Date d2 = new java.sql.Date(2021, 12, 31);
		java.sql.Date d = new java.sql.Date(time);

		Coupon coupon = new Coupon(1, 9, "sds", "dsd", d, d2, 10, 55.5, "tmuna");

		Company c1 = new Company();
		c1.setId(1);
		c1.setName("dabus");
		c1.setEmail("dabush@gmail.com");
		c1.setPassword("12345");
		Company c2 = new Company();
		c2.setId(2);
		c2.setName("vication");
		c2.setEmail("vicationh@gmail.com");
		c2.setPassword("vication12");
		Company c3 = new Company();
		c3.setId(3);
		c3.setName("McShimshon");
		c3.setEmail("NcShimshon@gmail.com");
		c3.setPassword("shomn12");
		Company c4 = new Company();
		c4.setId(4);
		c4.setName("electricity Wherhouse");
		c4.setEmail("Hashmal@gmail.com");
		c4.setPassword("bzzzz12");
		Company c5 = new Company();
		c5.setId(5);
		c5.setName("zikoFishes");
		c5.setEmail("salmon@gmail.com");
		c5.setPassword("locus12");
		Company c6 = new Company();
		c6.setId(6);
		c6.setName("mifgashNoam");
		c6.setEmail("pargiot@gmail.com");
		c6.setPassword("lafa12");
		Company c7 = new Company();
		c7.setId(7);
		c7.setName("flyNoam");
		c7.setEmail("Flynoam@gmail.com");
		c7.setPassword("airplain12");
		Company c8 = new Company();
		c8.setId(8);
		c8.setName("albert diry");
		c8.setEmail("milk@gmail.com");
		c8.setPassword("chees12");
		Company c9 = new Company();
		c9.setId(9);
		c9.setName("Moshe vintelator");
		c9.setEmail("windo@gmail.com");
		c9.setPassword("radoator12");
		Company c10 = new Company();
		c10.setId(10);
		c10.setName("shimon burger");
		c10.setEmail("burger@gmail.com");
		c10.setPassword("cheesburger12");

		DatabaseManager.init();
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		CompanyDBDAO companyDBDAO = new CompanyDBDAO();
		CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		couponDBDAO.addCoupon(coupon);
		System.out.println("END");

	}

}
