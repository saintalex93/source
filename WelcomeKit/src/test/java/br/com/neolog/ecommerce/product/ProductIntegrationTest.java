package br.com.neolog.ecommerce.product;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import io.restassured.http.ContentType;

public class ProductIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldAssertWhenSendOneProductViaPostAndRecieveSameProductInResponse()
    {

        final String json = "{\"code\":8, \"name\": \"Celular\", \"price\":10.50, \"description\":\"teste\", \"weight\":1.00, \"category\":{	\"id\":1,	\"code\":1, \"name\":\"Eletrônicos\"}}";
        final Product x = given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" )
            .as( Product.class );
        final Product y = given().contentType( ContentType.JSON ).when().get( "product/search/id/" + x.getId() )
            .as( Product.class );

        assertThat( x ).isEqualTo( y );
    }

    @Test
    public void shouldAssertWhenUpdateProductViaPost()
    {
        final Product y = given().contentType( ContentType.JSON ).when().get( "product/search/id/2" ).as( Product.class );
        final String json = "{\"id\":2,\"code\": 2,\"name\": \"Linux\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "product/update" ).as( Product.class );
        final Product x = given().contentType( ContentType.JSON ).when().get( "product/search/id/2" ).as( Product.class );
        assertThat( x.getName() ).doesNotContain( y.getName() );
    }

    @Test
    public void shouldAssertWhenProductIsInsertedAndDeletedViaPost()
    {
        final String json = "{\"code\": 2,\"name\": \"Linux\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        final Product x = given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" )
            .as( Product.class );
        final boolean productDeleted = given().contentType( ContentType.JSON ).body( json ).when()
            .post( "product/delete/" + x.getId() ) != null;

        assertThat( productDeleted == true );
    }

    @Test
    public void shouldAssertWhenReturnProductFindByContainedWord()
    {
        final String json = "{\"code\": 365,\"name\": \"Ubuntu\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" ).as( Product.class );

        final Product[] y = given().contentType( ContentType.JSON ).when().get( "product/search/contains?word=Ubuntu" )
            .as( Product[].class );
        assertThat( y != null );
    }

    @Test
    public void shouldThrowProductNotFoundException()
    {

        given().contentType( ContentType.JSON ).when().get( "product/search/id/22" ).then().assertThat()
            .statusCode( HttpStatus.SC_NOT_FOUND ).and().body( "message", Matchers.equalTo( "Produto não encontrado" ) );

    }

    @Test
    public void shouldThrowStockProductRemoveWithQuantityException()
    {
        given().contentType( ContentType.JSON ).when().post( "product/delete/1" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo(
                "Não é possivel um estoque com produtos com quantidade superior a 0." ) );

    }

    @Test
    public void shouldThrowCategoryFillExceptionWhenCategoryNotFillCode()
    {

        final String json = "{\"code\":8,\"name\": \"Celular\",\"price\":10.50,\"description\":\"teste\",\"weight\":1.00,\"category\":{}}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo( "A Categoria precisa ter um código" ) );

    }

    @Test
    public void sholdThrowProductDuplicatedException()
    {
        final String json = "{\"code\":1,\"name\": \"Celular\",\"price\":10.50,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "product/save" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo( "Produto com código duplicado" ) );
    }

}
