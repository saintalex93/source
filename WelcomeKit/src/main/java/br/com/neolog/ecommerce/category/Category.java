package br.com.neolog.ecommerce.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private Integer code;

	@Column(nullable = false)
	private String name;

	public Category() {

	}

	public Category(final Integer id, final Integer code, final String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Category(final Integer code, final String name) {
		this.code = code;
		this.name = name;
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

	@Override
	public String toString() {

		return MoreObjects.toStringHelper(this).add("Id: ", id).add("Código: ", code).add("Nome: ", name).toString();

	}

}
