package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the company_brands database table.
 * 
 */
@Entity
@Table(name="company_brands")
@NamedQuery(name="CompanyBrand.findAll", query="SELECT c FROM CompanyBrand c")
public class CompanyBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="companyId")
	private Company company;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="brandId")
	private Brand brand;

	public CompanyBrand() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}