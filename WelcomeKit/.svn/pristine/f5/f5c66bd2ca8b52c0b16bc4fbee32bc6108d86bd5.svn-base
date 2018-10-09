package br.com.neolog.ecommerce.stock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StockItem {

	@NotNull
	private Integer idProduct;
	@Min(message = "Quantidade deve ser maior que 1", value = 1)
	private int quantity;

	StockItem() {
	}

	StockItem(final Integer idProduct, final int quantity) {

		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(final Integer idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

}
