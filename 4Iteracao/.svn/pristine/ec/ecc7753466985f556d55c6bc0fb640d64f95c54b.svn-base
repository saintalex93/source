package br.com.neolog.product;

import static br.com.neolog.ProvideToken.provideToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.neolog.AbstractIntegrationTest;
import br.com.neolog.models.Product;
import io.restassured.http.ContentType;

public class ProductIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldAssertRecieveListOfAllProducts()
    {
        final Product[] productDB = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get(
            "product/all" )
            .as( Product[].class );
        assertThat( productDB != null );
    }

    @Test
    public void shouldAssertRecieveListOfProctsFilteredByCode()
    {
        final Product productDB = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get(
            "product/find/code/1" )
            .as( Product.class );
        assertThat( productDB != null );
    }

    @Test
    public void shouldAssertWhenSendOneProductViaPostAndRecieveSameProductInResponse()
    {

        final String json = "{\"id\": 2,\"code\": \"123\",\"name\": \"Windão Original\",\"price\": 20000,\"category\": {\"id\": 2,\"code\": \"2\",\"name\": \"Tecnológicos\"},\"description\": \"S.O\",\"weight\": 5000,\"volume\": 10000}";
        final Product x = given().header( "token", provideToken() ).contentType(
            ContentType.JSON ).body( json ).when().post( "product/add" ).then().extract().body().as( Product.class );
        final Product y = given().header( "token", provideToken() ).contentType(
            ContentType.JSON ).when().get( "product/find/" + x
                .getCode() )
            .as( Product.class );

        assertThat( x ).isEqualTo( y );
    }

    @Test
    public void shouldAssertWhenUpdateProductViaPost()
    {
        final Product y = given().header( "token", provideToken() ).contentType(
            ContentType.JSON ).when().get( "product/find/1" )
            .as( Product.class );
        final String json = "{\"id\": 1,\"code\": \"1\",\"name\": \"Linixão\",\"price\": 20000,\"category\": {\"id\": 2,\"code\": \"2\",\"name\": \"Tecnológicos\"},\"description\": \"S.O\",\"weight\": 5000,\"volume\": 10000}";
        final Product x = given().header( "token", provideToken() ).contentType( ContentType.JSON ).body( json ).when().post(
            "product/update" ).as(
                Product.class );
        assertThat( x.getName() ).doesNotContain( y.getName() );
    }

    @Test
    public void shouldAssertWhenProductIsInsertedAndDeletedViaPost()
    {
        final String json = "{\"id\": 6,\"code\": \"555442\",\"name\": \"Linixão\",\"price\": 20000,\"category\": {\"id\": 2,\"code\": \"2\",\"name\": \"Tecnológicos\"},\"description\": \"S.O\",\"weight\": 5000,\"volume\": 10000}";
        final Product x = given().header( "token", provideToken() ).contentType(
            ContentType.JSON ).body( json ).when().post(
                "product/add" )
            .then().extract().response().as( Product.class );

        final String response = given().header( "token", provideToken() ).contentType( ContentType.JSON ).body( json ).when()
            .post( "product/remove/" + x.getCode() ).then().extract().body().asString();

        assertThat( response ).contains( "Producto deletado com Succesfull" );

    }

}
