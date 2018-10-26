package br.com.neolog.ecommerce.authentication;

import static br.com.neolog.ecommerce.ProvideToken.provideToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.exceptions.CustomerInactiveException;
import br.com.neolog.ecommerce.exceptions.CustomerNotFoundException;
import br.com.neolog.ecommerce.exceptions.CustomerPasswordException;
import br.com.neolog.ecommerce.exceptions.ErrorDetails;
import br.com.neolog.ecommerce.exceptions.SessionNotFoundException;
import io.restassured.http.ContentType;

public class SessionIntegrationTest
    extends
        AbstractIntegrationTest

{

    @Test
    public void shouldAssertSuccessfulLogin()
    {

        final String token = given().contentType( ContentType.JSON ).when()
            .body( " {\"email\": \"session@santocodigo.com.br\",\"password\":\"Neolog123\"}" ).post( "session/login" ).asString();

        assertThat( token ).isNotNull();

    }

    @Test
    public void shouldAssertSuccessfulLogout()
    {
        final String token = given().contentType( ContentType.JSON ).when()
            .body( " {\"email\": \"session@santocodigo.com.br\",\"password\":\"Neolog123\"}" ).post( "session/login" ).asString();

        final String confirm = given().header( "token", token ).contentType( ContentType.JSON ).when()
            .post( "session/logout" ).then().extract().body().asString();

        assertThat( confirm ).containsIgnoringCase( "Sessão encerrada" );
    }

    @Test
    public void shouldAssertCustomerNotFoundException()
    {

        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body(
            " {\"email\": \"notfound@santocodigo.com.br\",\"password\":\"Neolog123\"}" )
            .post( "session/login" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertCustomerInactiveException()
    {

        final String jsonCustomer = "{\"email\": \"inactivecustomer@santocodigo.com.br\",\"name\": \"Teste Ctchulu\",\"password\": \"Neolog123\",\"inactive\":true}";
        given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body( jsonCustomer )
            .post( "customer/save" );

        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body(
            " {\"email\": \"inactivecustomer@santocodigo.com.br\",\"password\":\"Neolog123\"}" )
            .post( "session/login" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerInactiveException.class.getName() );

    }

    @Test
    public void shouldAssertCustomerPasswordException()
    {

        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body(
            " {\"email\": \"alex@santocodigo.com.br\",\"password\":\"Cthulu312\"}" )
            .post( "session/login" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerPasswordException.class.getName() );

    }

    @Test
    public void shouldAssertSessionNotFoundException()
    {
        final ErrorDetails bodyError = given().header( "token", "InvalidToken" ).contentType( ContentType.JSON ).when()
            .post( "session/logout" ).then().extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( SessionNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertTokenNotFound()
    {
        final ErrorDetails bodyError = given().contentType( ContentType.JSON ).when()
            .get( "product/list" ).then().extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getMessage() ).contains( "Token não encontrado" );

    }

    @Test
    public void shouldAssertInvalidToken()
    {
        final ErrorDetails bodyError = given().header( "token", "schrubles" ).contentType( ContentType.JSON ).when()
            .get( "product/list" ).then().extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getMessage() ).contains( "Token Inválido" );

    }

    @Test
    public void shouldAssertInactiveCustomer()
    {
        final ErrorDetails bodyError = given().header( "token", "inactiveCustomer" ).contentType( ContentType.JSON ).when()
            .get( "product/list" ).then().extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getMessage() ).contains( "Cliente Inativo" );

    }

    @Test
    public void shouldAssertExpirationToken()
    {
        final ErrorDetails bodyError = given().header( "token", "expirationCustomer" ).contentType( ContentType.JSON ).when()
            .get( "product/list" ).then().extract().body().as( ErrorDetails.class );

        assertThat( bodyError.getMessage() ).contains( "Tempo de sessão Esgotado. Refaça o login" );

    }
}
