package br.com.neolog.ecommerce.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.google.common.base.MoreObjects;

public class CustomerLogin
{
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public CustomerLogin()
    {
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

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "Email", email ).add( "Senha", password ).toString();
    }

}
