package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.EmailAlreadyInUseException;
import br.com.neolog.exceptions.UserNotFoundException;
import br.com.neolog.models.Customer;
import br.com.neolog.services.AuthenticationService;

@RestController
@RequestMapping( "session" )
public class SessionController
{
    private final AuthenticationService authenticationService;

    @Autowired
    public SessionController(
        final AuthenticationService authenticationService )
    {
        this.authenticationService = authenticationService;
    }

    @PostMapping( value = "login" )
    public String login(
        @RequestBody final Customer user )
        throws UserNotFoundException,
            EmailAlreadyInUseException
    {

        return authenticationService.login( user.getEmail(), user.getPassword() );
    }

    @GetMapping( value = "logout/{token}" )
    public boolean logout(
        @PathVariable final String token )
        throws Exception
    {

        return authenticationService.logout( token );
    }

}
