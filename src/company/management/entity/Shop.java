package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shops database table.
 * 
 */
@Entity
@Table(name="shops")
@NamedQuery(name="Shop.findAll", query="SELECT s FROM Shop s")
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int shopId;

	private String shopName;

	//bi-directional many-to-one association to SectorShop
	@OneToMany(mappedBy="shop")
	private List<SectorShop> sectorShops;

	//bi-directional many-to-one association to ShopProduct
	@OneToMany(mappedBy="shop")
	private List<ShopProduct> shopProducts;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="countryId")
	private Country country;

	public Shop() {
	}

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<SectorShop> getSectorShops() {
		return this.sectorShops;
	}

	public void setSectorShops(List<SectorShop> sectorShops) {
		this.sectorShops = sectorShops;
	}

	public SectorShop addSectorShop(SectorShop sectorShop) {
		getSectorShops().add(sectorShop);
		sectorShop.setShop(this);

		return sectorShop;
	}

	public SectorShop removeSectorShop(SectorShop sectorShop) {
		getSectorShops().remove(sectorShop);
		sectorShop.setShop(null);

		return sectorShop;
	}

	public List<ShopProduct> getShopProducts() {
		return this.shopProducts;
	}

	public void setShopProducts(List<ShopProduct> shopProducts) {
		this.shopProducts = shopProducts;
	}

	public ShopProduct addShopProduct(ShopProduct shopProduct) {
		getShopProducts().add(shopProduct);
		shopProduct.setShop(this);

		return shopProduct;
	}

	public ShopProduct removeShopProduct(ShopProduct shopProduct) {
		getShopProducts().remove(shopProduct);
		shopProduct.setShop(null);

		return shopProduct;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}