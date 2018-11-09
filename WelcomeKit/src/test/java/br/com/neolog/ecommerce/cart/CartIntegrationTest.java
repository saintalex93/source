package br.com.neolog.ecommerce.cart;

import static br.com.neolog.ecommerce.ProvideToken.provideToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.exceptions.CartCustomerException;
import br.com.neolog.ecommerce.exceptions.CartItemNotFoundException;
import br.com.neolog.ecommerce.exceptions.CartItemRemoveException;
import br.com.neolog.ecommerce.exceptions.CartItemStockException;
import br.com.neolog.ecommerce.exceptions.CartNotFoundException;
import br.com.neolog.ecommerce.exceptions.ErrorDetails;
import br.com.neolog.ecommerce.exceptions.ProductNotFoundException;
import br.com.neolog.ecommerce.exceptions.StockNotFoundException;
import br.com.neolog.ecommerce.stock.Stock;
import io.restassured.http.ContentType;

public class CartIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldAssertGetCartItemById1()
    {

        final Cart cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().get( "cart/search/1" ).then()
            .assertThat().statusCode( HttpServletResponse.SC_OK )
            .extract().body().as( Cart.class );

        assertThat( cart ).isNotNull();

    }

    @Test
    public void shouldAssertCartNotFoundExceptionWhenSearchCartWithID233()
    {

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().get(
            "cart/search/233" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND )
            .extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertCartCustomerExceptionWhenSearchCartOfOtherCustomer()
    {

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().get(
            "cart/search/2" ).then().assertThat().statusCode( HttpServletResponse.SC_BAD_REQUEST )
            .extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartCustomerException.class.getName() );

    }

    @Test
    public void shouldAssertGetAListOfCartItems()
    {

        final Cart[] cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().get( "cart/search" ).then()
            .assertThat().statusCode( HttpServletResponse.SC_OK )
            .extract().body().as( Cart[].class );

        assertThat( cart ).isNotNull();

    }

    @Test
    public void shouldAssertAddCartItemToCart()
    {
        final String jsonCartItem = "{\"productId\":1,\"quantity\":10}";
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/add-item-to-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart ).isNotNull();
    }

    @Test
    public void shouldAssertAddCartItemToCartWhenCartItemExists()
    {
        final String jsonCartItem = "{\"productId\":1,\"quantity\":1}";
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/add-item-to-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart ).isNotNull();
    }

    @Test
    public void shouldAssertProductNotFoundExceptionWhenProductIdNotFound()
    {
        final String jsonCartItem = "{\"productId\":123,\"quantity\":10}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/add-item-to-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( ProductNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertStockNotFoundException()
    {
        final String jsonCartItem = "{\"productId\":3,\"quantity\":1000}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/add-item-to-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( StockNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertProductNotFoundExceptionWhenRemoveQuantity()
    {
        final String jsonCartItem = "{\"productId\":5123,\"quantity\":1000}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/removeQuantity" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( ProductNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertStockNotFoundExceptionWhenRemoveQuantity()
    {
        final String jsonCartItem = "{\"productId\":3,\"quantity\":1000}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/removeQuantity" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( StockNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertCartItemNotFoundExceptionWhenRemoveQuantity()
    {
        final String jsonCartItem = "{\"productId\":4,\"quantity\":1}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "testRemoveQuantity" ).when().body(
            jsonCartItem )
            .post( "cart/remove-quantity" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartItemNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertCartItemRemoveExceptionWhenRemoveQuantity()
    {
        final String jsonCartItem = "{\"productId\":1,\"quantity\":120152}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "testRemoveQuantity" ).when().body(
            jsonCartItem )
            .post( "cart/removeQuantity" ).then().assertThat().statusCode( HttpServletResponse.SC_BAD_REQUEST ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartItemRemoveException.class.getName() );

    }

    @Test
    public void shouldAssertCartNotFoundExceptionWhenRemoveQuantity()
    {
        final String jsonCartItem = "{\"productId\":1,\"quantity\":120152}";

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "cartNotFound" ).when().body(
            jsonCartItem )
            .post( "cart/removeQuantity" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertRemoveCartItem()
    {
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", "testRemoveItem" ).when()
            .post( "cart/remove-cart-item/7" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart.getTotalValue() ).isLessThanOrEqualTo( 25000 );
    }

    @Test
    public void shouldAssertCartItemNotFoundExceptionWhenRemoveCartItem()
    {

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "testRemoveItem" ).when()
            .post( "cart/remove-cart-item/22" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartItemNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertCartCustomerExceptionWhenRemoveCartItem()
    {

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "testRemoveItem" ).when()
            .post( "cart/remove-cart-item/1" ).then().assertThat().statusCode( HttpServletResponse.SC_BAD_REQUEST ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartCustomerException.class.getName() );

    }

    @Test
    public void shouldAssertRemoveProductQuantityFromCartItem()
    {

        final String jsonCartItem = "{\"productId\":2,\"quantity\":1}";
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", "testRemoveQuantity" ).when().body( jsonCartItem )
            .post( "cart/removeQuantity" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart.getTotalValue() ).isEqualTo( 4800 );

    }

    @Test
    public void shouldAssertRemoveProductQuantityFromExistsCartItem()
    {

        final String jsonCartItem = "{\"productId\":1,\"quantity\":1}";
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", "testRemoveItemExistsCartItem" ).when().body(
            jsonCartItem )
            .post( "cart/removeQuantity" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart.getTotalValue() ).isEqualTo( 2500 );

    }

    @Test
    public void shouldAssertGetActiveCustomerCart()
    {
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when()
            .get( "cart/customer-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart ).isNotNull();
    }

    @Test
    public void shouldAssertCheckout()
    {
        final Stock stock = given().contentType( ContentType.JSON ).header( "token", "testeCheckout" ).when()
            .get( "stock/search/product/2" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Stock.class );

        final Cart cart = given().contentType( ContentType.JSON ).header( "token", "testeCheckout" ).when()
            .post( "cart/checkout" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        final Stock currentStock = given().contentType( ContentType.JSON ).header( "token", "testeCheckout" ).when()
            .get( "stock/search/product/2" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Stock.class );

        assertThat( cart.getStatus() ).isEqualTo( CartStatus.FINISHED );
        assertThat( currentStock.getQuantity() ).isLessThan( stock.getQuantity() );
    }

    @Test
    public void shouldAssertThrowCartItemNotFoundExceptionWhenCheckout()
    {

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "cartitem" ).when()
            .post( "cart/checkout" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartItemNotFoundException.class.getName() );
    }

    @Test
    public void shouldAssertThrowCartItemStockExceptionWhenCheckoutCartItemsWithQuantityGreaterThanStock()
    {

        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "cartItemStream" ).when()
            .post( "cart/checkout" ).then().assertThat().statusCode( HttpServletResponse.SC_BAD_REQUEST ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartItemStockException.class.getName() );
    }

    @Test
    public void shouldAssertThrowCartItemStockExceptionWhenAddItemToCartWithoutStock()
    {
        final String jsonCartItem = "{\"productId\":1,\"quantity\":104545}";
        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().body( jsonCartItem )
            .post( "cart/add-item-to-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_BAD_REQUEST ).extract().body().as(
                ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CartItemStockException.class.getName() );
    }

    @Test
    public void shouldAssertReturnCartItemsOfCart1()
    {
        final CartItem[] cartItems = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when()
            .get( "cart/get-cart-item/1" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as(
                CartItem[].class );
        assertThat( cartItems ).isNotNull();
    }

    @Test
    public void shouldAssertCancelActiveCart()
    {
        final Cart cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when()
            .post( "cart/cancel-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().body().as( Cart.class );

        assertThat( cart.getStatus() ).isEqualTo( CartStatus.CANCELLED );
    }

    @Test
    public void shouldAssertCartNotFoundExceptionWhenCancelActiveCart()
    {
        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "cartNotFound" ).when()
            .post( "cart/cancel-cart" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );
        assertThat( bodyError.getDetails() ).contains( CartNotFoundException.class.getName() );
    }

    @Test
    public void shouldAssertCartNotFoundExceptionWhenCheckOut()
    {
        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).header( "token", "cartFinished" ).when()
            .post( "cart/checkout" ).then().assertThat().statusCode( HttpServletResponse.SC_NOT_FOUND ).extract().body().as(
                ErrorDetails.class );
        assertThat( bodyError.getDetails() ).contains( CartNotFoundException.class.getName() );
    }

    @Test
    public void shouldAssertCartItemNotFoundExceptionWhenSearchCartItemsOfCartNotFound()
    {
        final List<CartItem> cartItems = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when()
            .get( "cart/get-cart-item/17666" ).then().assertThat().statusCode( HttpServletResponse.SC_OK ).extract().jsonPath().getList( "" );

        assertThat( cartItems ).isEmpty();

    }

}
