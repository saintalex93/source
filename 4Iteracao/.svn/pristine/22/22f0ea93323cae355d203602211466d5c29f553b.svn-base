package br.com.neolog.services;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.pojo.Customer;
import br.com.neolog.pojo.Session;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.UserRepository;

/**
 * Classe responsável por fornecer serviços de autenticação do
 * {@link Customer}, criar {@link Session}, fazer login e logout.
 *
 * @author igor.kurman
 */
@Component
public class AuthenticationService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    TokenService tokenService;

    /**
     * Este método criar uma {@link Session} para o {@link Customer} corrente.
     *
     * @param user é o {@link Customer} corrente ao qual a session deve ser
     *        atribuída.
     */
    private void createSession(
        final Customer user )
    {
        final Session session = new Session();
        final Calendar calendar = Calendar.getInstance();
        final Calendar calendar2 = Calendar.getInstance();

        session.setCustomer( user );
        session.setLoginDate( DateTime.now() );

        calendar2.add( Calendar.DATE, 1 );

        session.setExpirationDate( calendar2 );
        session.setToken( "" + calendar.getTimeInMillis() );

        sessionRepository.save( session );
    }

    /**
     * Método que verifica se o {@code email} informado pertence a algum
     * {@link Customer} existente no sistema e se o {@code password} informado
     * é válido.
     *
     * @param email que o método usa para procurar o {@link Customer} no
     *        sistema.
     * @param password para verificar a autenticidade do {@link Customer}.
     * @return NÃO DEFINIDO
     * @throws UserNotFoundException se o {@code email} informado não retornar
     *         nenhum usuário da base de dados.
     */
    public String login(
        final String email,
        final String password )
        throws UserNotFoundException
    {
        final Customer user = userRepository.findByEmail( email );

        // ShaPasswordEncoder encoder = new ShaPasswordEncoder();

        if( user == null ) {
            throw new UserNotFoundException( "Usuário não existe no sistema!!!" );
        }
        //
        // if (encoder.isPasswordValid(user.getPassword(), password, null)) {
        //
        // if (sessionRepository.findByUserEmail(email) == null) {
        // createSession(user);
        // } else {
        // Session session = sessionRepository.findByUserEmail(email);
        //
        // sessionRepository.delete(session.getId());
        // createSession(user);
        // }
        //
        // }
        return sessionRepository.findByCustomerEmail( email ).getToken();
    }

    /**
     * Termina a sessão do {@link Customer} corrente.
     *
     * @param token para validar a operação.
     * @return
     * @throws Exception
     */
    public boolean logout(
        final String token )
        throws Exception
    {

        if( tokenService.validateToken( token ) ) {

            CurrentUserHolder.setUser( null );
        } else {
            throw new Exception( "Token inv�lido" );
        }

        return true;
    }
}