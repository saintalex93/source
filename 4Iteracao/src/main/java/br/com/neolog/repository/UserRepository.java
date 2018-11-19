package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Customer;

public interface UserRepository
    extends
        CrudRepository<Customer,Serializable>
{

    public Customer findByName(
        String name );

    public Customer findByEmail(
        String email );

    public Customer findByEmailAndPassword(
        String email,
        String password );

}