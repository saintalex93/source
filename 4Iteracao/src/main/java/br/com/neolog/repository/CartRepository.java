package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Cart;
import br.com.neolog.pojo.User;

public interface CartRepository extends CrudRepository<Cart, Serializable> {

	public Cart findByStatus(String status);

	public Cart findByUserAndStatus(User user, String satus);

}