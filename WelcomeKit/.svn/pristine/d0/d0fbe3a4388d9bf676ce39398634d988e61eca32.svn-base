package br.com.neolog.ecommerce.product;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import io.restassured.http.ContentType;

public class ProductIntegrationDropUpdateTest extends AbstractIntegrationTest {

	@Test
	public void shouldReturnTrueWhenDeleteAProduct() {

		given().contentType(ContentType.JSON).when().post("product/delete/2").then().log().everything().assertThat()
				.statusCode(HttpStatus.SC_OK).and().body(equalTo("false"));
	}

	@Test
	public void shouldUpdateProduct() {

		final String json = "{\"id\": 1,\"cod\": 3,\"name\": \"Teste\",\"price\": 2500.0,\"description\": \"PlasmaTron\",\"weight\": 1.5,\"category\":{\"id\":1,\"cod\":3,\"name\":\"Eletrônicos\"}}";
		final Product x = given().contentType(ContentType.JSON).body(json).when().post("product/update")
				.as(Product.class);

		final Product y = given().contentType(ContentType.JSON).body("").when().get("product/search/id/1")
				.as(Product.class);

		assertThat(x).isEqualTo(y);
	}

}
