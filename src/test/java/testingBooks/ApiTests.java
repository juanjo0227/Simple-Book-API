package testingBooks;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    public void getStatus() {
        String endpoint = "https://simple-books-api.glitch.me/status";
        given().
                when().
                    get(endpoint).
                then().
                    assertThat().statusCode(200).
                    assertThat().body("status", equalTo("OK"));
    }

    @Test
    public void getListOfBooks() {
        String endpoint = "https://simple-books-api.glitch.me/books";
        given().
                queryParam("type", "non-fiction").
        when().
                get(endpoint).
        then().
                assertThat().statusCode(200).
                body("book.size()", greaterThan(0)).
                body("book.id", everyItem(notNullValue())).
        log().body();
    }
}
