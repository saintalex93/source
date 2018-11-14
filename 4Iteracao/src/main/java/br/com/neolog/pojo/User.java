package br.com.neolog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank
	@Size(min = 3, max = 50, message = "O nome deve ter entre 5 e 50 caracteres!!!")
	private String name;

	@NotBlank
	@Column(unique = true)
	@Size(min = 6, max = 50, message = "O email deve ter entre 5 e 50 caracteres!!!")
	private String email;

	@NotBlank
	@Size(min = 3, max = 50, message = "O password deve ter entre 3 e 40 caracteres!!!")
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User u = (User) o;
		if (this.id == u.getId())
			return true;
		if (this.name.equals(u.getName()))
			if (this.email == u.getEmail())
				if (this.password.equals(u.getPassword()))
					return true;
		return false;

	}

	@Override
	public String toString() {
		return this.name + " " + this.email;
	}
}
