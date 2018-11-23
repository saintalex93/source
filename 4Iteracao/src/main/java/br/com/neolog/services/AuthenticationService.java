package br.com.neolog.services;

import java.nio.charset.StandardCharsets;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.models.Customer;
import br.com.neolog.models.Session;
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
    private UserRepository customerRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    TokenService tokenService;

    /**
     * Este método criar uma {@link Session} para o {@link Customer} corrente.
     *
     * @param customer é o {@link Customer} corrente ao qual a session deve ser
     *        atribuída.
     */
    private Session createSession(
        final Customer customer )
    {
        final Session session = new Session();
        final DateTime dateTime = DateTime.now();

        session.setCustomer( customer );
        session.setLoginDate( DateTime.now() );

        session.setExpirationDate( dateTime.plusHours( 2 ) );
        session.setToken( generateToken() );

        return sessionRepository.save( session );
    }

    private static String generateToken()
    {
        return String.valueOf( Double.valueOf( Math.random() * 1000 ).longValue() + System.currentTimeMillis() );
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

        final Customer customer = customerRepository.findByEmailAndPassword( email, encryptPassword( password ) );

        if( customer == null ) {
            throw new UserNotFoundException( "Usu�rio n�o existe no sistema!!!" );
        }

        final Session session = createSession( customer );

        return session.getToken();
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

        if( tokenService.clearExpirationToken( token ) ) {
            CurrentUserHolder.setUser( null );
        } else {
            throw new Exception( "Token inv�lido ou com erro" );
        }

        return true;
    }

    public static String encryptPassword(
        final String password )
    {
        final Hasher hasher = Hashing.sha512().newHasher();
        hasher.putString( password, StandardCharsets.UTF_8 );
        return hasher.hash().toString();
    }
}