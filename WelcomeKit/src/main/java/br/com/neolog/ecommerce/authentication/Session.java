package br.com.neolog.ecommerce.authentication;

import javax.persistence.Column;
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

import org.hibernate.annotations.Type;
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
    @Column( name = "expiration_date" )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime" )
    private DateTime expirationDate;
    @NotNull
    @Column( name = "login_date" )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime" )
    private DateTime loginDate;
    @NotNull
    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "customer", foreignKey = @ForeignKey( name = "fk_session_customer" ) )
    private Customer customer;

    public Session()
    {
    }

    public Session(
        final String token,
        final DateTime expirationDate,
        final DateTime loginDate,
        final Customer user )
    {
        super();
        this.token = token;
        this.expirationDate = expirationDate;
        this.loginDate = loginDate;
        this.customer = user;
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

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(
        final Customer user )
    {
        this.customer = user;
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
            .add( "User", customer ).toString();
    }

}