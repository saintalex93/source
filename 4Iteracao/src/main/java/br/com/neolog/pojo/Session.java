package br.com.neolog.pojo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table( name = "session" )
public class Session
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    private String token;

    @OneToOne
    @JoinColumn( name = "user_id", referencedColumnName = "id" )
    private Customer customer;

    @Column( name = "login_date" )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime" )
    private DateTime loginDate;

    @Column( name = "expiration_date" )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime" )
    private Calendar expirationDate;

    public Integer getId()
    {
        return id;
    }

    public void setId(
        final Integer id )
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

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(
        final Customer user )
    {
        this.customer = user;
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

    public Calendar getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(
        final Calendar expirationDate )
    {
        this.expirationDate = expirationDate;
    }

}
