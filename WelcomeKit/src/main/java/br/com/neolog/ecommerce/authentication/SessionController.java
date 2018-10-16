package br.com.neolog.ecommerce.authentication;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.ecommerce.customer.Customer;
import br.com.neolog.ecommerce.customer.CustomerLogin;
import br.com.neolog.ecommerce.customer.CustomerService;

@RestController
@RequestMapping( "session" )
public class SessionController
{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping( value = "login" )
    public ResponseEntity<String> logging(
        @RequestBody @Valid final CustomerLogin customer )
    {
        return new ResponseEntity<String>( sessionService.login( customer ), HttpStatus.OK );
    }

    @PostMapping( value = "save" )
    public ResponseEntity<Customer> save(
        @RequestBody @Valid final Customer customer )
    {
        return new ResponseEntity<Customer>( customerService.save( customer ), HttpStatus.OK );
    }

}
