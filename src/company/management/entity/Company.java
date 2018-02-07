package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int companyId;

	private String address;

	private String companyName;

	private String nipt;

	//bi-directional many-to-one association to CompanyBrand
	@OneToMany(mappedBy="company")
	private List<CompanyBrand> companyBrands;

	//bi-directional many-to-one association to CompanyDepartment
	@OneToMany(mappedBy="company")
	private List<CompanyDepartment> companyDepartments;

	public Company() {
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNipt() {
		return this.nipt;
	}

	public void setNipt(String nipt) {
		this.nipt = nipt;
	}

	public List<CompanyBrand> getCompanyBrands() {
		return this.companyBrands;
	}

	public void setCompanyBrands(List<CompanyBrand> companyBrands) {
		this.companyBrands = companyBrands;
	}

	public CompanyBrand addCompanyBrand(CompanyBrand companyBrand) {
		getCompanyBrands().add(companyBrand);
		companyBrand.setCompany(this);

		return companyBrand;
	}

	public CompanyBrand removeCompanyBrand(CompanyBrand companyBrand) {
		getCompanyBrands().remove(companyBrand);
		companyBrand.setCompany(null);

		return companyBrand;
	}

	public List<CompanyDepartment> getCompanyDepartments() {
		return this.companyDepartments;
	}

	public void setCompanyDepartments(List<CompanyDepartment> companyDepartments) {
		this.companyDepartments = companyDepartments;
	}

	public CompanyDepartment addCompanyDepartment(CompanyDepartment companyDepartment) {
		getCompanyDepartments().add(companyDepartment);
		companyDepartment.setCompany(this);

		return companyDepartment;
	}

	public CompanyDepartment removeCompanyDepartment(CompanyDepartment companyDepartment) {
		getCompanyDepartments().remove(companyDepartment);
		companyDepartment.setCompany(null);

		return companyDepartment;
	}

}