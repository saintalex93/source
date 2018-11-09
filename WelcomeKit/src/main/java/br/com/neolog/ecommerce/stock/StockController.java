package br.com.neolog.ecommerce.stock;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "stock" )
public class StockController
{
    @Autowired
    private StockService service;

    @GetMapping( value = "search/all" )
    public ResponseEntity<List<Stock>> getStock()
    {
        return ResponseEntity.ok( service.getStock() );
    }

    @GetMapping( value = "search" )
    public ResponseEntity<Page<Stock>> getStockPage(
        @RequestParam( name = "size" ) final Optional<Integer> size,
        @RequestParam( name = "page" ) final Optional<Integer> page )
    {
        return ResponseEntity.ok( service.findPage( page.orElse( 0 ), size.orElse( 50 ) ) );
    }

    @GetMapping( value = "search/active" )
    public ResponseEntity<List<Stock>> getStockByQuantityGreaterThanZero()
    {
        return new ResponseEntity<List<Stock>>( service.getStockByQuantityGreaterThanZero(), HttpStatus.OK );
    }

    @GetMapping( value = "search/inactive" )
    public ResponseEntity<List<Stock>> getStockByQuantityLessThanOne()
    {
        return new ResponseEntity<List<Stock>>( service.getStockByQuantityLessThanOne(), HttpStatus.OK );
    }

    @GetMapping( value = "search/product/{productId}" )
    public ResponseEntity<Stock> getStockByProductId(
        @PathVariable( "productId" ) final int productId )
    {
        return new ResponseEntity<Stock>( service.getStockByProductId( productId ), HttpStatus.OK );
    }

    @GetMapping( value = "search/max" )
    public ResponseEntity<Stock> getStockMaxProduct()
    {
        return new ResponseEntity<Stock>( service.getStockMaxProduct(), HttpStatus.OK );
    }

    @GetMapping( value = "search/min" )
    public ResponseEntity<Stock> getStockMinProduct()
    {
        return new ResponseEntity<Stock>( service.getStockMinProduct(), HttpStatus.OK );
    }

    @PostMapping( value = "create" )
    public ResponseEntity<Stock> addStock(
        @Valid @RequestBody final StockItem stockItem )
    {
        return new ResponseEntity<Stock>( service.createStock( stockItem ), HttpStatus.OK );
    }

    @PostMapping( value = "add-quantity" )
    public ResponseEntity<Stock> setQuantityAdd(
        @Valid @RequestBody final StockItem stockItem )
    {
        return new ResponseEntity<Stock>( service.addStock( stockItem ), HttpStatus.OK );
    }

    @PostMapping( value = "remove-quantity" )
    public ResponseEntity<Stock> setQuantityRemove(
        @Valid @RequestBody final StockItem stockItem )
    {
        return new ResponseEntity<Stock>( service.removeStock( stockItem ), HttpStatus.OK );
    }

    @PostMapping( value = "remove/{id}" )
    public ResponseEntity<Boolean> removeStock(
        @Valid @PathVariable( "id" ) final int id )
    {
        return new ResponseEntity<Boolean>( service.deleteStock( id ), HttpStatus.OK );
    }

}
