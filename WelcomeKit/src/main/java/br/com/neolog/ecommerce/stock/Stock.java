package br.com.neolog.ecommerce.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.neolog.ecommerce.product.Product;

@Entity
@Table(name = "stock")
// @SequenceGenerator(name = "Stock_Sequence", sequenceName = "Stock_Generator",
// allocationSize = 1, initialValue = 1)
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private int quantity;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_stock_product"))
	private Product product;

	public Stock() {
	}

	public Stock(final int quantity, final Product stock) {
		this.quantity = quantity;
		this.product = stock;
	}

	public Stock(final Integer id, final int quantity, final Product stock) {
		this.id = id;
		this.quantity = quantity;
		this.product = stock;
	}

	public Integer getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

}
