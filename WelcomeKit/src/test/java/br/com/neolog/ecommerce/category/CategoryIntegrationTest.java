package br.com.neolog.ecommerce.category;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import io.restassured.http.ContentType;

public class CategoryIntegrationTest
    extends
        AbstractIntegrationTest
{
    @Test
    public void shouldAssertWhenSendAnCategoryViaPostAndRecieveSameCategoryInResponse()
    {

        final String json = "{\"code\": 321, \"name\": \"CategoryTest\"}";
        final Category x = given().contentType( ContentType.JSON ).body( json ).when().post( "category/save" )
            .as( Category.class );
        final Category y = given().contentType( ContentType.JSON ).when().get( "category/search/" + x.getCode() )
            .as( Category.class );

        assertThat( x ).isEqualTo( y );
    }

    @Test
    public void shouldAssertWhenUpdateCategoryViaPost()
    {
        final Category y = given().contentType( ContentType.JSON ).when().get( "category/search/1" ).as( Category.class );
        final String json = "{\"id\": 1,\"code\": 1,\"name\": \"Teste\"}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "category/update" ).as( Category.class );
        final Category x = given().contentType( ContentType.JSON ).when().get( "category/search/1" ).as( Category.class );
        assertThat( x.getName() ).doesNotContain( y.getName() );
    }

    @Test
    public void shouldAssertWhenCategoryIsInsertedAndDeletedViaPost()
    {
        final String json = "{\"code\": 512,\"name\": \"Deletecat\"}";
        final Category x = given().contentType( ContentType.JSON ).body( json ).when().post( "category/save" )
            .as( Category.class );
        final boolean CategoryDeleted = given().contentType( ContentType.JSON ).body( json ).when()
            .post( "category/delete/" + x.getId() ) != null;

        assertThat( CategoryDeleted == true );
    }

    @Test
    public void shouldThrowCategoryRemoveWithProductException()
    {
        given().contentType( ContentType.JSON ).when().post( "category/delete/1" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", equalTo(
                "Não é possivel remover uma categoria com produtos atrelados a ela." ) );
    }

    @Test
    public void shouldAssertWhenReturnCategoryFindByContainedWord()
    {
        final String json = "{\"code\": 420,\"name\": \"TeaPot\"}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "category/save" ).as( Category.class );
        final Category[] y = given().contentType( ContentType.JSON ).when().get( "category/search/contains?word=Tea" )
            .as( Category[].class );
        assertThat( y != null );
    }

    @Test
    public void shouldThrowCategoryNotFoundException()
    {
        given().contentType( ContentType.JSON ).when().get( "category/search/22" ).then().assertThat()
            .statusCode( HttpStatus.SC_NOT_FOUND ).and().body( "message", equalTo( "Categoria não encontrada" ) );
    }

    @Test
    public void sholdThrowCategoryDuplicatedException()
    {
        final String json = "{\"code\": 1,\"name\": \"TeaPot\"}";
        given().contentType( ContentType.JSON ).body( json ).when().post( "category/save" ).then().assertThat()
            .statusCode( HttpStatus.SC_BAD_REQUEST ).and().body( "message", Matchers.equalTo( "Código da categoria duplicado" ) );
    }

}
