package br.com.neolog.ecommerce.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository
    extends
        JpaRepository<Session,Integer>
{

    Session findByToken(
        String token );

}
