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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.neolog.ecommerce.productcategory.Category;

@Entity
@Table(name = "product")
@SequenceGenerator(name = "productSequence", sequenceName = "productGenerator", initialValue = 1, allocationSize = 1)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productGenerator")
	@Column(nullable = false, updatable = false)
	private Integer id;

	@NotNull
	@Column(unique = true, nullable = false)
	private Integer cod;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private double price;

	private String description;

	@NotNull
	@Column(nullable = false)
	private double weight;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", foreignKey = @ForeignKey(name = "fk_stock_product"))
	private Category category;

	public Product() {
	}

	public Product(final Integer id, final Integer cod, final String name, final double price, final String description,
			final double weight, final Category category) {
		this.id = id;
		this.cod = cod;
		this.name = name;
		this.price = price;
		this.description = description;
		this.weight = weight;
		this.category = category;

	}

	public Product(final Integer cod, final String name, final double price, final String description,
			final double weight, final Category category) {
		this.cod = cod;
		this.name = name;
		this.price = price;
		this.description = description;
		this.weight = weight;
		this.category = category;

	}

	public int getId() {
		return id;
	}

	public int getCod() {
		return cod;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public double getWeight() {
		return weight;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cod == null ? 0 : cod.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		final Product other = (Product) obj;
		if (cod == null) {
			if (other.cod != null) {
				return false;
			}
		} else if (!cod.equals(other.cod)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", cod=" + cod + ", name=" + name + ", price=" + price + ", description="
				+ description + ", weight=" + weight + ", category=" + category + "]";
	}

}
