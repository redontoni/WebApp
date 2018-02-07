package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sector_brand database table.
 * 
 */
@Entity
@Table(name="sector_brand")
@NamedQuery(name="SectorBrand.findAll", query="SELECT s FROM SectorBrand s")
public class SectorBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Sector
	@ManyToOne
	@JoinColumn(name="sectorId")
	private Sector sector;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="brandId")
	private Brand brand;

	public SectorBrand() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}