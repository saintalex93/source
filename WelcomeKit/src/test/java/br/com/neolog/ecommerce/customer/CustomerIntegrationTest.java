package br.com.neolog.ecommerce.customer;

import static br.com.neolog.ecommerce.ProvideToken.provideToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.exceptions.CustomerDuplicatedEmailException;
import br.com.neolog.ecommerce.exceptions.CustomerFillException;
import br.com.neolog.ecommerce.exceptions.CustomerNotFoundException;
import br.com.neolog.ecommerce.exceptions.ErrorDetails;
import io.restassured.http.ContentType;

public class CustomerIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldAssertGetCustomerById1()
    {
        final Customer customer = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when()
            .get( "customer/search/1" )
            .as( Customer.class );

        assertThat( customer ).isNotNull();
        assertThat( customer.getEmail() ).contains( "santocodigo" );

    }

    @Test
    public void shouldAssertGetAListCustomer()
    {
        final Customer[] customerList = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when()
            .get( "customer/search" )
            .as( Customer[].class );

        assertThat( customerList ).isNotNull();

    }

    @Test
    public void shouldAssertSuccessOfCreateCustomer()
    {
        final String jsonCustomer = "{\"email\": \"teste@santocodigo.com.br\",\"name\": \"Teste Ctchulu\",\"password\": \"Neolog123\",\"inactive\":false}";
        final Customer customer = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body( jsonCustomer )
            .post( "customer/save" ).as( Customer.class );

        assertThat( customer ).isNotNull();
        assertThat( customer.getName() ).contains( "Ctchulu" );
    }

    @Test
    public void shouldAssertCustomerDuplicatedEmailException()
    {

        final String jsonCustomer = "{\"id\":1,\"email\": \"alex@santocodigo.com.br\",\"name\": \"Teste Ctchulu\",\"password\": \"Neolog123\",\"inactive\":false}";
        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body( jsonCustomer )
            .post( "customer/save" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerDuplicatedEmailException.class.getName() );

    }

    @Test
    public void shouldAssertCustomerFillExceptionWhenUpdateCustomerWithoutID()
    {

        final String jsonCustomer = "{\"email\": \"alex@santocodigo.com.br\",\"name\": \"Teste Ctchulu\",\"password\": \"Neolog123\",\"inactive\":false}";
        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body( jsonCustomer )
            .post( "customer/update" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerFillException.class.getName() );

    }

    @Test
    public void shouldAssertCustomerNotFoundExceptionWhenUpdateCustomerWithWrongId()
    {

        final String jsonCustomer = "{\"id\":13,\"email\": \"alex@santocodigo.com.br\",\"name\": \"Teste Ctchulu\",\"password\": \"Neolog123\",\"inactive\":false}";
        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body( jsonCustomer )
            .post( "customer/update" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerNotFoundException.class.getName() );

    }

    @Test
    public void shouldAssertCustomerDuplicatedEmailExceptionWhenUpdateCustomerWithDuplicatedEmail()
    {

        final String jsonCustomer = "{\"id\":2,\"email\": \"alex@santocodigo.com.br\",\"name\": \"Teste Ctchulu\",\"password\": \"Neolog123\",\"inactive\":false}";
        final ErrorDetails bodyError = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body( jsonCustomer )
            .post( "customer/update" ).then().extract().as( ErrorDetails.class );

        assertThat( bodyError.getDetails() ).contains( CustomerDuplicatedEmailException.class.getName() );

    }

    @Test
    public void shouldAssertUpdateCustomer()
    {
        final String jsonCustomer = "{\"id\":2,\"email\": \"teste@update.com.br\",\"name\": \"Alex Santos\",\"password\": \"Neolog123\",\"inactive\":false}";

        final Customer updatedCustomer = given().header( "token", provideToken() ).contentType( ContentType.JSON ).when().body(
            jsonCustomer )
            .post( "customer/update" ).as( Customer.class );

        assertThat( updatedCustomer.getEmail() ).doesNotContain( "santocodigo" );

    }

    @Test
    public void shouldAssertInactiveCustomer()
    {

        final Customer inactiveCustomer = given().header( "token", "customerInactive" ).contentType( ContentType.JSON )
            .when()
            .post( "customer/inactive" ).as( Customer.class );

        assertThat( inactiveCustomer.getInactive() ).isEqualTo( true );
    }

}
