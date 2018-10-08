package br.com.neolog.ecommerce.stock;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.product.Product;
import io.restassured.http.ContentType;

public class StockIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldAssertWhenSendAStockViaPostAndRecieveSameStockInResponse()
    {

        final String json = "{\"code\":8,\"name\": \"Celular\",\"price\":10.50,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        final Product product = given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" ).as( Product.class );

        final String jsonStock = "{\"idProduct\":" + product.getId() + ",\"quantity\":131}";
        final Stock x = given().contentType( ContentType.JSON ).body( jsonStock ).when().post( "stock/create" )
            .as( Stock.class );
        final Stock y = given().contentType( ContentType.JSON ).when().get( "stock/search/product/" + product.getId() ).as( Stock.class );
        assertThat( x ).isEqualTo( y );
    }

    @Test
    public void shouldAssertAddProductInStock()
    {

        final Stock product = given().contentType( ContentType.JSON ).when().get( "stock/search/product/1" ).as( Stock.class );
        product.setQuantity( 100 );
        final String json = "{\"idProduct\":1,\"quantity\":100}";
        final Stock x = given().contentType( ContentType.JSON ).body( json ).when().post( "stock/addQuantity" )
            .as( Stock.class );

        assertThat( product ).isEqualTo( x );
    }

    @Test
    public void shouldDeleteStockWhenQuantityIsZero()
    {

        final String json = "{\"code\":18,\"name\": \"Celular\",\"price\":10.50,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        final Product product = given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" ).as( Product.class );

        given().contentType( ContentType.JSON ).body( "{\"idProduct\":" + product.getId() + ",\"quantity\":100}" )
            .when().post(
                "stock/create" )
            .as( Stock.class );

        given().contentType( ContentType.JSON ).body( "{\"idProduct\":" + product.getId() + ",\"quantity\":100}" ).when().post(
            "stock/removeQuantity" )
            .as( Stock.class );

        final boolean bool = given().contentType( ContentType.JSON ).when().post( "stock/remove/3" ) != null;

        assertThat( bool ).isEqualTo( true );
    }

    @Test
    public void shouldThrowStockNotFoundException()
    {

        final String json = "{\"code\":24,\"name\": \"Celza\",\"price\":10.50,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        final Product product = given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" ).as( Product.class );

        given().contentType( ContentType.JSON ).body( "{\"idProduct\":" + product.getId() + ",\"quantity\":5}" )
            .when().post(
                "stock/addQuantity" ).then().assertThat()
            .statusCode( HttpStatus.SC_NOT_FOUND ).and().body( "message", Matchers.equalTo(
                "Estoque não encontrado" ) );
    }

    @Test
    public void shouldThrowStockDuplicatedProductException()
    {

        given().contentType( ContentType.JSON ).body( "{\"idProduct\":1,\"quantity\":100}" )
            .when().post(
                "stock/create" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo(
                "Estoque Duplicado. O produto já está em outro estoque." ) );
    }

    @Test
    public void shouldThrowStockFillExceptionWhenQuantityAreGreaterThanIntegerMaxValue()
    {
        given().contentType( ContentType.JSON ).body( "{\"idProduct\":1,\"quantity\":" + Integer.MAX_VALUE + "}" )
            .when().post(
                "stock/addQuantity" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo(
                "Quantidade informada é maior do que a permitida" ) );
    }

    @Test
    public void shouldThrowStockProductRemoveWithQuantityException()
    {
        given().contentType( ContentType.JSON ).when().post( "stock/remove/1" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo(
                "Não é possivel um estoque com produtos com quantidade superior a 0." ) );

    }

}
