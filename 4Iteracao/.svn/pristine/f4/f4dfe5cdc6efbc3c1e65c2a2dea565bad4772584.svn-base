package br.com.neolog.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	Product product;

	int quantity;

	public OrderItem() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// public Cart getCart() {
	// return cart;
	// }

	// public void setCart(Cart cart) {
	// this.cart = cart;
	// }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("PRODUCT", product)
				.add("QUANTITY", quantity).toString();
	}

}
