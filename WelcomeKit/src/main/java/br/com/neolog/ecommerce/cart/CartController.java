package br.com.neolog.ecommerce.cart;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.ecommerce.customer.Customer;

@RestController
@RequestMapping( value = "cart" )
public class CartController
{

    @Autowired
    private CartService cartService;

    @GetMapping( "search" )
    public ResponseEntity<List<Cart>> listAll()
    {
        return new ResponseEntity<List<Cart>>( cartService.listAll(), HttpStatus.OK );

    }

    @GetMapping( "search/{id}" )
    public ResponseEntity<Cart> listAll(
        @PathVariable( "id" ) final int id )
    {
        return new ResponseEntity<Cart>( cartService.findById( id ), HttpStatus.OK );

    }

    @PostMapping( "customerCart" )
    public ResponseEntity<Cart> listAll(
        @RequestBody @Valid final Customer customer )
    {
        return new ResponseEntity<Cart>( cartService.getActiveCustomerCart( customer ), HttpStatus.OK );

    }

}
