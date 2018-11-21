package br.com.neolog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.pojo.Customer;

public interface UserRepository
    extends
        JpaRepository<Customer,Integer>
{

    public Customer findByName(
        String name );

    public Customer findByEmail(
        String email );

    public Customer findByEmailAndPassword(
        String email,
        String password );

}