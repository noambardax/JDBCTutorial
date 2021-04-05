package com.Db;

import java.util.List;

import com.beans.Company;

public interface CompanyDao {
	boolean isCompanyExist(Company company);

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(Company company);

	Company getOneCompany(int companyID);

	List<Company> getAllCompanies();
}
