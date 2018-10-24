package br.com.neolog.ecommerce.cart;

import static br.com.neolog.ecommerce.ProvideToken.provideToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import io.restassured.http.ContentType;

public class CartIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldAssertGetCartItemById1()
    {

        final Cart cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().get( "cart/search/1" ).then()
            .extract().body().as( Cart.class );

        assertThat( cart ).isNotNull();

    }

    @Test
    public void shouldAssertGetAListOfCartItems()
    {

        final Cart[] cart = given().contentType( ContentType.JSON ).header( "token", provideToken() ).when().get( "cart/search" ).then()
            .extract().body().as( Cart[].class );

        assertThat( cart ).isNotNull();

    }

    @Test
    public void shouldAssertAddCartItemToCart()
    {

    }

    @Test
    public void shouldAssertRemoveCartItem()
    {

    }

    @Test
    public void shouldAssertRemoveProductQuantityFromCartItem()
    {

    }

    @Test
    public void shouldAssertGetActiveCustomerCart()
    {

    }

    @Test
    public void shouldAssertCheckout()
    {

    }

    @Test
    public void shouldAssertCancelActiveCart()
    {

    }

}
