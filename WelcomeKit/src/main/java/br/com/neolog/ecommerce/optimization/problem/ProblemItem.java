package br.com.neolog.ecommerce.optimization.problem;

import java.util.Objects;

import com.google.common.base.MoreObjects;


//implements Comparable<ProblemItem> 
public class ProblemItem {
	private int productCode;
	private long value;
	private long quantity;

	public ProblemItem() {
	}

	public ProblemItem(final int productCode, final long value, final long quantity) {

		this.productCode = productCode;
		this.value = value;
		this.quantity = quantity;

	}

	public int getProductCode() {
		return productCode;
	}

	public long getValue() {
		return value;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(final long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {

		return MoreObjects.toStringHelper(this).add("Product Code", productCode).add("Value", value)
				.add("Quantity", quantity).toString();

	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProblemItem)) {
			return false;
		}
		final ProblemItem other = (ProblemItem) obj;
		return Objects.equals(getProductCode(), other.getProductCode()) && Objects.equals(getValue(), other.getValue());
	}

//	@Override
//	public int compareTo(ProblemItem problemItem) {
//
//		long compareValue = ((ProblemItem) problemItem).getValue();
//
//		return (int) (this.value - compareValue);
//
//	}

}
