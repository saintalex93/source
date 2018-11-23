package br.com.neolog.category;

import static br.com.neolog.ProvideToken.provideToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.neolog.AbstractIntegrationTest;
import br.com.neolog.models.Category;
import io.restassured.http.ContentType;

public class CategoryIntegrationTest
    extends
        AbstractIntegrationTest
{
    @Test
    public void shouldAssertWhenFindAllCategoriesIsNotNull()
    {
        final Category[] categoryDB = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get(
            "category/all" )
            .as( Category[].class );
        assertThat( categoryDB != null );
    }

    @Test
    public void shouldAssertWhenFindCategoryCode()
    {
        final Category categoryDB = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get(
            "category/find?code=2" )
            .as( Category.class );

        final Category category = Category.create( "2", "Tecnológicos" );
        assertThat( category ).isEqualTo( categoryDB );
    }

    @Test
    public void shouldAssertWhenSendAnCategoryViaPostAndRecieveSameCategoryInResponse()
    {
        final String json = "{\"code\": 321, \"name\": \"CategoryTest\"}";
        final Category x = given().header( "token", provideToken() ).contentType( ContentType.JSON ).body( json ).when().post(
            "category/add" )
            .as( Category.class );
        final Category y = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get( "category/find?code=" + x
            .getCode() )
            .as( Category.class );
        assertThat( x ).isEqualTo( y );
    }

    @Test
    public void shouldAssertWhenUpdateCategoryViaPost()
    {
        final Category y = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get( "category/find?code=1" )
            .as( Category.class );
        final String json = "{\"id\": 1,\"code\": 1,\"name\": \"Teste\"}";
        given().header( "token", provideToken() ).contentType( ContentType.JSON ).body( json ).when().post( "category/update" ).as(
            Category.class );
        final Category x = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().get( "category/find?code=1" )
            .as( Category.class );
        assertThat( x.getName() ).doesNotContain( y.getName() );
    }

    @Test
    public void shouldAssertWhenCategoryIsInsertedAndDeletedViaPost()
    {
        final String json = "{\"code\": 512,\"name\": \"Deletecat\"}";
        final Category x = given().header( "token", provideToken() ).contentType( ContentType.JSON ).body( json ).when().post(
            "category/add" )
            .as( Category.class );
        final String CategoryDeleted = given().header( "token", provideToken() ).contentType( ContentType.JSON ).body( json ).when()
            .post( "category/remove?code=" + x.getCode() ).then().extract().body().asString();

        assertThat( CategoryDeleted == "Categoria removida com sucesso!" );
    }

}
