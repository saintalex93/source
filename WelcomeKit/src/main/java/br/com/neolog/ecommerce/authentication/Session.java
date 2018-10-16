package br.com.neolog.ecommerce.authentication;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import br.com.neolog.ecommerce.customer.Customer;

@Entity
@Table( name = "session" )
public class Session
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    @NotBlank
    private String token;
    @NotNull
    private DateTime expirationDate;
    @NotNull
    private DateTime loginDate;
    @NotNull
    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user", foreignKey = @ForeignKey( name = "fk_session_user" ) )
    private Customer user;

    public Session()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(
        final int id )
    {
        this.id = id;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(
        final String token )
    {
        this.token = token;
    }

    public DateTime getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(
        final DateTime expirationDate )
    {
        this.expirationDate = expirationDate;
    }

    public DateTime getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(
        final DateTime loginDate )
    {
        this.loginDate = loginDate;
    }

    public Customer getUser()
    {
        return user;
    }

    public void setUser(
        final Customer user )
    {
        this.user = user;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode( this.getId(), this.getToken() );
    }

    @Override
    public boolean equals(
        final Object obj )
    {

        if( this == obj ) {
            return true;
        }

        if( obj instanceof Session ) {
            return true;
        }

        final Session session = (Session) obj;

        return Objects.equal( this.getId(), session.getId() ) && Objects.equal( this.getToken(), session.getToken() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "Id", id )
            .add( "Token", token )
            .add( "Expiration Date", expirationDate )
            .add( "Login Date", loginDate )
            .add( "User", user ).toString();
    }

}
