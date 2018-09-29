package br.com.neolog.ecommerce.productcategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@SequenceGenerator(name = "categoryGenerator", sequenceName = "categorySequence", initialValue = 1, allocationSize = 1)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "categorySequence")
	@Column(updatable = false, nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, updatable = false, unique = true)
	private Integer cod;

	@Column(nullable = false)
	private String name;

	public Category() {

	}

	public Category(final Integer id, final Integer cod, final String name) {
		this.id = id;
		this.cod = cod;
		this.name = name;
	}

	public Category(final Integer cod, final String name) {
		this.cod = cod;
		this.name = name;
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

}
