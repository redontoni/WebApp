package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int countryId;

	private String countryName;

	//bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name="currencyId")
	private Currency currency;

	//bi-directional many-to-one association to Shop
	@OneToMany(mappedBy="country")
	private List<Shop> shops;

	public Country() {
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<Shop> getShops() {
		return this.shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public Shop addShop(Shop shop) {
		getShops().add(shop);
		shop.setCountry(this);

		return shop;
	}

	public Shop removeShop(Shop shop) {
		getShops().remove(shop);
		shop.setCountry(null);

		return shop;
	}

}