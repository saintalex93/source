package br.com.neolog.ecommerce.product;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.neolog.ecommerce.AbstractIntegrationTest;
import io.restassured.http.ContentType;

public class ProductIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldAssertWhenSendOneProductViaPostAndRecieveSameProductInResponse() {

		final String json = "{\"code\":8, \"name\": \"Celular\", \"price\":10.50, \"description\":\"teste\", \"weight\":1.00, \"category\":{	\"id\":1,	\"code\":1, \"name\":\"Eletrônicos\"}}";
		final Product x = given().contentType(ContentType.JSON).body(json).when().post("product/save")
				.as(Product.class);
		final Product y = given().contentType(ContentType.JSON).when().get("product/search/id/" + x.getId())
				.as(Product.class);

		assertThat(x).isEqualTo(y);
	}

	@Test
	public void shouldAssertWhenUpdateProductViaPost() {
		final Product y = given().contentType(ContentType.JSON).when().get("product/search/id/2").as(Product.class);
		final String json = "{\"id\":2,\"code\": 2,\"name\": \"Linux\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
		given().contentType(ContentType.JSON).body(json).when().post("product/update").as(Product.class);
		final Product x = given().contentType(ContentType.JSON).when().get("product/search/id/2").as(Product.class);
		assertThat(x.getName()).doesNotContain(y.getName());
	}

	@Test
	public void shouldAssertWhenProductIsInsertedAndDeletedViaPost() {
		final String json = "{\"code\": 2,\"name\": \"Linux\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
		final Product x = given().contentType(ContentType.JSON).body(json).when().post("product/save")
				.as(Product.class);
		final boolean productDeleted = given().contentType(ContentType.JSON).body(json).when()
				.post("product/delete/" + x.getId()) != null;

		assertThat(productDeleted == true);
	}

	@Test
	public void shouldAssertTrueWhenProductIsInsertedAndDeletedViaPost() {
		final String json = "{\"code\": 2,\"name\": \"Linux\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
		final Product x = given().contentType(ContentType.JSON).body(json).when().post("product/save")
				.as(Product.class);
		final boolean productDeleted = given().contentType(ContentType.JSON).body(json).when()
				.post("product/delete/" + x.getId()) != null;

		assertThat(productDeleted == true);
	}

	@Test
	public void shouldAssertWhenReturnProductFindByContainedWord() {
		final String json = "{\"code\": 365,\"name\": \"Ubuntu\",\"price\":20.00,\"description\":\"teste\",\"weight\":1.00,\"category\":{\"id\":1,\"code\":1,\"name\":\"Eletrônicos\"}}";
		given().contentType(ContentType.JSON).body(json).when().post("product/save").as(Product.class);

		final Product[] y = given().contentType(ContentType.JSON).when().get("product/search/contains?word=Ubuntu")
				.as(Product[].class);
		assertThat(y != null);
	}

	/*
	 * Contains By Word
	 */

	// @Test
	// public void
	// shouldAssertTrueWhenSendAListOfProductsAndRecieveTheSameList() {
	// final Category eletronico = new Category(1, 1, "Eletronicos");
	// final Category tecnologico = new Category(2, 2, "Tecnológicos");
	// final Product product1 = new Product(1, 1, "TV", 2500.0, "PlasmaTron",
	// 1.5,
	// eletronico);
	// final Product product2 = new Product(2, 2, "Windão Original", 2500.0,
	// "S.O",
	// 5000.0, tecnologico);
	// final Product product3 = new Product(3, 3, "TV", 2500.0, "PlasmaTron",
	// 1.5,
	// eletronico);
	// final List<Product> products = new ArrayList<>();
	// products.add(product1);
	// products.add(product2);
	// products.add(product3);
	// final List<Product> body =
	// given().contentType(ContentType.JSON).when().get("product/search/").then().extract()
	// .jsonPath().getList("", Product.class);
	// assertThat(body).isEqualTo(products);
	// }
	//
}

// @Test
// public void shouldAssertNameOfSecondItemInTheList() {
//
// given().contentType(ContentType.JSON).when().get("product/search/name/2").then().log().everything().assertThat()
// .statusCode(HttpStatus.SC_OK).and().body(equalTo("Windão Original"));
// }