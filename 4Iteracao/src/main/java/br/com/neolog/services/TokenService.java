package br.com.neolog.services;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.models.Session;
import br.com.neolog.repository.SessionRepository;

@Component
public class TokenService
{
    @Autowired
    private SessionRepository sessionRepository;

    public boolean validateToken(
        final String token )
    {
        final Session session = sessionRepository.findByToken( token );
        if( session != null
            && DateTime.now().isBefore( session.getExpirationDate() ) ) {
            return true;
        }
        return false;
    }

    public boolean clearExpirationToken(
        final String token )
    {
        final Session session = sessionRepository.findByToken( token );

        if( session != null ) {
            session.setExpirationDate( DateTime.now() );
            sessionRepository.save( session );
            return true;
        }

        return false;

    }

}
