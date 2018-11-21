package br.com.neolog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.pojo.Session;

public interface SessionRepository
    extends
        JpaRepository<Session,Integer>
{

    public Session findByCustomerEmail(
        String email );

    public Session findByToken(
        String token );

}