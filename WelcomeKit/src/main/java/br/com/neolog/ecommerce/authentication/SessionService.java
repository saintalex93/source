package br.com.neolog.ecommerce.authentication;

import static br.com.neolog.ecommerce.authentication.AuthenticationUtils.encryptPassword;
import static br.com.neolog.ecommerce.authentication.AuthenticationUtils.generateToken;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.customer.Customer;
import br.com.neolog.ecommerce.customer.CustomerLogin;
import br.com.neolog.ecommerce.customer.CustomerRepository;
import br.com.neolog.ecommerce.exceptions.CustomerInactiveException;
import br.com.neolog.ecommerce.exceptions.CustomerNotFoundException;
import br.com.neolog.ecommerce.exceptions.CustomerPasswordException;

@Component
public class SessionService
{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public String login(
        final CustomerLogin customerLogin )
    {
        final Customer customer = customerRepository.findByEmail( customerLogin.getEmail() );

        if( customer == null ) {
            throw new CustomerNotFoundException();
        }
        if( customer.getInactive() == true ) {
            throw new CustomerInactiveException();
        }
        if( ! customer.getPassword().equals( encryptPassword( customerLogin.getPassword() ) ) ) {
            throw new CustomerPasswordException();
        }

        return generateSession( customer );
    }

    private String generateSession(
        final Customer customer )
    {
        final DateTime dateTime = DateTime.now();
        final Session session = new Session( generateToken(), dateTime.plusHours( 2 ), dateTime, customer );
        sessionRepository.save( session );
        return session.getToken();
    }

    public Session verifyToken(
        final String token )
    {
        return sessionRepository.findByToken( token );

    }

}
