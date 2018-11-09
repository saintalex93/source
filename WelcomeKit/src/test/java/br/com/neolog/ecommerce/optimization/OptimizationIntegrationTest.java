package br.com.neolog.ecommerce.optimization;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import br.com.neolog.ecommerce.ProvideToken;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OptimizationIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldReturnFullStock()
    {
        final Solution solution = RestAssured.given().contentType( ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "optimization/150021.00/" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Solution.class );
        assertThat( solution ).isNotNull();
    }

    @Test
    public void shouldReturnEmptyListWhenTargetLesserThanMinProductValue()
    {
        final Solution solution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "optimization/2" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Solution.class );
        assertThat( solution.getSolutionItems() ).isEmpty();
    }

    @Test
    public void shouldReturnAproximatedValueWhenTargetIs1500()
    {
        final Solution solution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "optimization/1500.00/" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Solution.class );
        assertThat( solution.getResult() * 100 ).isEqualByComparingTo(  1495d );
    }

    @Test
    public void shouldReturnExactValueWhenTargetIs1415()
    {
        final Solution solution = RestAssured.given().contentType(
            ContentType.JSON ).header( "token", ProvideToken.provideToken() )
            .when().post( "optimization/1420.00/" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( Solution.class );
        assertThat( solution.getResult() * 100 ).isEqualByComparingTo( 1415d );
    }
}
