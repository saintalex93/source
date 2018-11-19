package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Session;

public interface SessionRepository
    extends
        CrudRepository<Session,Serializable>
{

    public Session findByCustomerEmail(
        String email );

    public Session findByToken(
        String token );

}