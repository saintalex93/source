package br.com.neolog.optimization;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.AbstractIntegrationTest;
import br.com.neolog.ProvideToken;
import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.models.ProblemType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OptimizationIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldReturnFullStock()
    {

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 15002100, ProblemType.VALUE );

        final PresentationSolution presentationSolution = RestAssured.given().contentType( ContentType.JSON ).header( "token", ProvideToken
            .provideToken() )
            .when().body( optmizationHolder ).post( "optimization/optimize" ).then()
            .statusCode( HttpStatus.SC_OK )
            .extract().body().as( PresentationSolution.class );
        Assertions.assertThat( presentationSolution ).isNotNull();
    }

    // @Test
    // public void shouldReturnEmptyListWhenTargetLesserThanMinProductValue()
    // {
    // final Suggestion suggest = RestAssured.given().contentType(
    // ContentType.JSON ).header( "token", ProvideToken.provideToken() )
    // .when().post( "suggest-cart/2" ).then()
    // .statusCode( HttpStatus.SC_OK )
    // .extract().body().as( Suggestion.class );
    // assertThat( suggest.getSuggestionItem() ).isEmpty();
    // }
    //
    // @Test
    // public void shouldReturnAproximatedValueWhenTargetIs1500()
    // {
    // final Suggestion suggest = RestAssured.given().contentType(
    // ContentType.JSON ).header( "token", ProvideToken.provideToken() )
    // .when().post( "suggest-cart/1500.00/" ).then()
    // .statusCode( HttpStatus.SC_OK )
    // .extract().body().as( Suggestion.class );
    // assertThat( suggest.getResult() * 100 ).isEqualByComparingTo( 1420d );
    // }
    //
    // @Test
    // public void shouldReturnExactValueWhenTargetIs1420()
    // {
    // final Suggestion suggest = RestAssured.given().contentType(
    // ContentType.JSON ).header( "token", ProvideToken.provideToken() )
    // .when().post( "suggest-cart/1420.00/" ).then()
    // .statusCode( HttpStatus.SC_OK )
    // .extract().body().as( Suggestion.class );
    // assertThat( suggest.getResult() * 100 ).isEqualByComparingTo( 1420d );
    // }
}
