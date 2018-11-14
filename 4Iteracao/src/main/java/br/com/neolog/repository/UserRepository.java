package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.User;

public interface UserRepository extends CrudRepository<User, Serializable> {

	public User findByName(String name);

	public User findByEmail(String email);

}