package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shop_products database table.
 * 
 */
@Entity
@Table(name="shop_products")
@NamedQuery(name="ShopProduct.findAll", query="SELECT s FROM ShopProduct s")
public class ShopProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int amount;

	//bi-directional many-to-one association to Shop
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shop shop;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productsId")
	private Product product;

	public ShopProduct() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}