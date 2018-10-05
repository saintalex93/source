package br.com.neolog.ecommerce.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import br.com.neolog.ecommerce.category.Category;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Integer id;

	@NotNull
	@Column(unique = true, nullable = false)
	private Integer code;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@Min(message = "N�o pode ser inferior a 1 centavo", value = 1)
	@Column(nullable = false)
	private long price;

	private String description;

	@Column(nullable = false)
	private long weight;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", foreignKey = @ForeignKey(name = "fk_stock_product"))
	private Category category;

	public Product() {
	}

	public Product(final Integer id, final Integer code, final String name, final double price,
			final String description, final double weight, final Category category) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = Math.round(price * 10 * 10);
		this.description = description;
		this.weight = Math.round(weight * 10 * 10);
		this.category = category;

	}

	@JsonCreator
	public Product(@JsonProperty("code") final Integer code, @JsonProperty("name") final String name,
			@JsonProperty("price") final long price, @JsonProperty("description") final String description,
			@JsonProperty("weight") final double weight, @JsonProperty("category") final Category category) {
		this.code = code;
		this.name = name;
		this.price = Math.round(price * 10 * 10);
		this.description = description;
		this.weight = Math.round(weight * 10 * 10);
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {

		return price / 100.0;
	}

	public String getDescription() {
		return description;
	}

	public double getWeight() {
		return weight / 100.0;
	}

	public Category getCategory() {
		return category;
	}

	private void setPrice(final double price) {
		this.price = Math.round(price * 10 * 10);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (code == null ? 0 : code.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		final Product other = (Product) obj;
		return Objects.equal(other.getId(), this.getId()) && Objects.equal(other.getCode(), this.getCode());
	}

	@Override
	public String toString() {

		return MoreObjects.toStringHelper(this).add("Id: ", id).add("Code: ", code).add("Name: ", name)
				.add("Price: ", price).add("Description: ", description).add("Weight: ", weight)
				.add("Category: ", weight).toString();

	}

}
