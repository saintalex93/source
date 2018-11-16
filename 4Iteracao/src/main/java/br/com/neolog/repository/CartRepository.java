package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Cart;
import br.com.neolog.pojo.Customer;

public interface CartRepository extends CrudRepository<Cart, Serializable> {

	public Cart findByStatus(String status);

	public Cart findByUserAndStatus(Customer user, String satus);

}