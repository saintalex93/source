package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.OrderItem;

public interface OrderItemRepository extends
		CrudRepository<OrderItem, Serializable> {

}