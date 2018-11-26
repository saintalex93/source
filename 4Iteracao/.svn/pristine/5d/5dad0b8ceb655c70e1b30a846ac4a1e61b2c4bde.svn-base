package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.EmailAlreadyInUseException;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.models.Customer;
import br.com.neolog.services.CustomerService;

@RestController
public class CustomerController
{
    private final CustomerService CustomerService;

    @Autowired
    public CustomerController(
        final CustomerService CustomerService )
    {
        this.CustomerService = CustomerService;
    }

    @RequestMapping( value = "/user/add", method = RequestMethod.POST )
    public String addUser(
        @RequestBody final Customer user )
        throws EmailAlreadyInUseException
    {
        CustomerService.addUser( user );
        return "Usuário adicionado com sucesso!";
    }

    @RequestMapping( value = "/user/remove", method = RequestMethod.DELETE )
    public String removeUser(
        @RequestBody final String email )
        throws UserNotFoundException
    {
        CustomerService.removeUser( email );
        return "Usuário removido com sucesso!";
    }

    @RequestMapping( value = "user/find", method = RequestMethod.POST )
    public String find(
        @RequestBody final String email )
        throws UserNotFoundException
    {
        final Customer user = CustomerService.find( email );
        final String result = "NAME: " + user.getName() + " | EMAIL: "
            + user.getEmail();
        return result;
    }

    @RequestMapping( value = "user/all", method = RequestMethod.GET )
    public String findAll()
    {
        String result = "USU�?RIOS CADASTRADOS" + "\n";
        for( final Customer u : CustomerService.findAll() ) {
            result = result.concat( "NAME: " + u.getName() + " | EMAIL: "
                + u.getEmail() + "\n" );
        }
        return result;
    }

}
