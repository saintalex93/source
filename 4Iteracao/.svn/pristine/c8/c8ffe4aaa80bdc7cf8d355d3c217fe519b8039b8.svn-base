package br.com.neolog.pojo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String code;
	@Column
	private String name = "";

	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.01")
	private double price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	private String description = "";
	private double weight;
	private double volume;

	public Product() {

	}

	public Product(Integer id, String name, Category category,
			String description, double weight, double volume) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.weight = weight;
		this.volume = volume;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Product)) {
			return false;
		}
		Product p = (Product) o;

		if (this.code.equals(p.code))
			if (this.name.equals(p.getName()))
				if (this.category.equals(p.category))
					if (this.description.equals(p.description))
						if (this.weight == p.weight)
							if (this.volume == p.volume)
								return true;

		return false;
	}

	@Override
	public String toString() {

		return "Name: " + this.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description);
	}
}
