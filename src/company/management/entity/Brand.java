package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name="brands")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int brandId;

	private String brandName;

	//bi-directional many-to-one association to CompanyBrand
	@OneToMany(mappedBy="brand")
	private List<CompanyBrand> companyBrands;

	//bi-directional many-to-one association to SectorBrand
	@OneToMany(mappedBy="brand")
	private List<SectorBrand> sectorBrands;

	public Brand() {
	}

	public int getBrandId() {
		return this.brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<CompanyBrand> getCompanyBrands() {
		return this.companyBrands;
	}

	public void setCompanyBrands(List<CompanyBrand> companyBrands) {
		this.companyBrands = companyBrands;
	}

	public CompanyBrand addCompanyBrand(CompanyBrand companyBrand) {
		getCompanyBrands().add(companyBrand);
		companyBrand.setBrand(this);

		return companyBrand;
	}

	public CompanyBrand removeCompanyBrand(CompanyBrand companyBrand) {
		getCompanyBrands().remove(companyBrand);
		companyBrand.setBrand(null);

		return companyBrand;
	}

	public List<SectorBrand> getSectorBrands() {
		return this.sectorBrands;
	}

	public void setSectorBrands(List<SectorBrand> sectorBrands) {
		this.sectorBrands = sectorBrands;
	}

	public SectorBrand addSectorBrand(SectorBrand sectorBrand) {
		getSectorBrands().add(sectorBrand);
		sectorBrand.setBrand(this);

		return sectorBrand;
	}

	public SectorBrand removeSectorBrand(SectorBrand sectorBrand) {
		getSectorBrands().remove(sectorBrand);
		sectorBrand.setBrand(null);

		return sectorBrand;
	}

}