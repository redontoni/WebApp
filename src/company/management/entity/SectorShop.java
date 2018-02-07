package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sector_shop database table.
 * 
 */
@Entity
@Table(name="sector_shop")
@NamedQuery(name="SectorShop.findAll", query="SELECT s FROM SectorShop s")
public class SectorShop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Sector
	@ManyToOne
	@JoinColumn(name="sectorId")
	private Sector sector;

	//bi-directional many-to-one association to Shop
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shop shop;

	public SectorShop() {
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

	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}