package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the sectors database table.
 * 
 */
@Entity
@Table(name = "sectors")
@NamedQuery(name = "Sector.findAll", query = "SELECT s FROM Sector s")
public class Sector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sectorId;

	private String sectorName;

	// bi-directional many-to-one association to DepartmentSector

	@OneToMany(mappedBy = "sector",cascade=CascadeType.ALL)
	private List<DepartmentSector> departmentSectors;

	// bi-directional many-to-one association to SectorBrand
	@OneToMany(mappedBy = "sector")
	private List<SectorBrand> sectorBrands;

	// bi-directional many-to-one association to SectorShop
	@OneToMany(mappedBy = "sector")
	private List<SectorShop> sectorShops;

	@Column(name="enabled",columnDefinition="BIT")
	private boolean enabled=true;

	public Sector() {
	}

	public int getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorName() {
		return this.sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public List<DepartmentSector> getDepartmentSectors() {
		return this.departmentSectors;
	}

	public void setDepartmentSectors(List<DepartmentSector> departmentSectors) {
		this.departmentSectors = departmentSectors;
	}

	public DepartmentSector addDepartmentSector(DepartmentSector departmentSector) {
		getDepartmentSectors().add(departmentSector);
		departmentSector.setSector(this);

		return departmentSector;
	}

	public DepartmentSector removeDepartmentSector(DepartmentSector departmentSector) {
		getDepartmentSectors().remove(departmentSector);
		departmentSector.setSector(null);

		return departmentSector;
	}

	public List<SectorBrand> getSectorBrands() {
		return this.sectorBrands;
	}

	public void setSectorBrands(List<SectorBrand> sectorBrands) {
		this.sectorBrands = sectorBrands;
	}

	public SectorBrand addSectorBrand(SectorBrand sectorBrand) {
		getSectorBrands().add(sectorBrand);
		sectorBrand.setSector(this);

		return sectorBrand;
	}

	public SectorBrand removeSectorBrand(SectorBrand sectorBrand) {
		getSectorBrands().remove(sectorBrand);
		sectorBrand.setSector(null);

		return sectorBrand;
	}

	public List<SectorShop> getSectorShops() {
		return this.sectorShops;
	}

	public void setSectorShops(List<SectorShop> sectorShops) {
		this.sectorShops = sectorShops;
	}

	public SectorShop addSectorShop(SectorShop sectorShop) {
		getSectorShops().add(sectorShop);
		sectorShop.setSector(this);

		return sectorShop;
	}

	public SectorShop removeSectorShop(SectorShop sectorShop) {
		getSectorShops().remove(sectorShop);
		sectorShop.setSector(null);

		return sectorShop;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}