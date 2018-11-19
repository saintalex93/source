package br.com.neolog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table( name = "customer" )
public class Customer
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "customer_sequence" )
    @SequenceGenerator( name = "customer_sequence", sequenceName = "customer_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Size( min = 3, max = 50, message = "O nome deve ter entre 5 e 50 caracteres!!!" )
    @NotBlank
    private String name;

    @NotBlank
    @Column( unique = true )
    @Size( min = 6, max = 50, message = "O email deve ter entre 5 e 50 caracteres!!!" )
    private String email;

    @NotBlank
    @Size( min = 3, max = 50, message = "O password deve ter entre 3 e 40 caracteres!!!" )
    private String password;

    private boolean inactive;

    public Integer getId()
    {
        return id;
    }

    public void setId(
        final Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(
        final String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(
        final String email )
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(
        final String password )
    {
        this.password = password;
    }

    public boolean isInactive()
    {
        return inactive;
    }

    public void setInactive(
        final boolean inactive )
    {
        this.inactive = inactive;
    }

    @Override
    public boolean equals(
        final Object o )
    {
        if( this == o ) {
            return true;
        }
        if( ! ( o instanceof Customer ) ) {
            return false;
        }
        final Customer u = (Customer) o;
        if( this.id == u.getId() ) {
            return true;
        }
        if( this.name.equals( u.getName() ) ) {
            if( this.email == u.getEmail() ) {
                if( this.password.equals( u.getPassword() ) ) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public String toString()
    {
        return this.name + " " + this.email;
    }
}
