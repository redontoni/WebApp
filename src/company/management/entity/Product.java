package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productsId;

	private int productsAmount;

	private String productsBrand;

	private String productsName;

	private BigDecimal productsPrice;

	//bi-directional many-to-one association to ShopProduct
	@OneToMany(mappedBy="product")
	private List<ShopProduct> shopProducts;

	public Product() {
	}

	public int getProductsId() {
		return this.productsId;
	}

	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}

	public int getProductsAmount() {
		return this.productsAmount;
	}

	public void setProductsAmount(int productsAmount) {
		this.productsAmount = productsAmount;
	}

	public String getProductsBrand() {
		return this.productsBrand;
	}

	public void setProductsBrand(String productsBrand) {
		this.productsBrand = productsBrand;
	}

	public String getProductsName() {
		return this.productsName;
	}

	public void setProductsName(String productsName) {
		this.productsName = productsName;
	}

	public BigDecimal getProductsPrice() {
		return this.productsPrice;
	}

	public void setProductsPrice(BigDecimal productsPrice) {
		this.productsPrice = productsPrice;
	}

	public List<ShopProduct> getShopProducts() {
		return this.shopProducts;
	}

	public void setShopProducts(List<ShopProduct> shopProducts) {
		this.shopProducts = shopProducts;
	}

	public ShopProduct addShopProduct(ShopProduct shopProduct) {
		getShopProducts().add(shopProduct);
		shopProduct.setProduct(this);

		return shopProduct;
	}

	public ShopProduct removeShopProduct(ShopProduct shopProduct) {
		getShopProducts().remove(shopProduct);
		shopProduct.setProduct(null);

		return shopProduct;
	}

}