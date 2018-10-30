package br.com.neolog.ecommerce.customer;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "customer" )
public class Customer
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "customer_sequence" )
    @SequenceGenerator( name = "customer_sequence", sequenceName = "customer_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;
    @NotBlank
    @Pattern( regexp = "[a-z0-9]+[a-z0-9.]+@[a-z0-9]+\\.[a-z0-9]+(\\.[a-z0-9]+)?", message = "Formato de email inv�lido" )
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private boolean inactive;

    public Customer()
    {

    }

    public Integer getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
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

    public boolean getInactive()
    {
        return inactive;
    }

    public void setInactive(
        final boolean inactive )
    {
        this.inactive = inactive;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getId() );
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }

        if( ! ( obj instanceof Customer ) ) {
            return false;
        }

        final Customer user = (Customer) obj;
        return Objects.equals( getId(), user.getId() ) &&
            Objects.equals( getEmail(), user.getEmail() ) &&
            Objects.equals( getPassword(), user.getPassword() ) &&
            Objects.equals( getName(), user.getName() ) &&
            Objects.equals( getInactive(), user.getInactive() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "Id", id )
            .add( "Email", email )
            .add( "Name", name )
            .add( "Password", password )
            .add( "Active", inactive )
            .toString();
    }

}
