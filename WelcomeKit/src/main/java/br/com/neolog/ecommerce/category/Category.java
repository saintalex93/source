package br.com.neolog.ecommerce.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
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

	public Integer getId() {
		return id;
	}

	public Integer getCod() {
		return cod;
	}

	public String getName() {
		return name;
	}

}
