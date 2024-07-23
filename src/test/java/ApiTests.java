import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void getlistUsersTest() {
        int numberPages = 2;
        given()
                .log().all()
                .param("page", numberPages)
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("per_page", is(6));
//                .body("page", is("data.lengt()"));
    }

    @Test
    void postGreateTest() {
        String name = "morpheus";
        String job = "leader";
        String requestBody = "{\"name\": \"" + name + "\", \"job\": \"" + job + "\"}";
        given()
                .log().all()
                .body(requestBody)
                .contentType(JSON)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("job", is(job))
                .body("name", is(name));
    }

    @Test
    void putUpdateTest() {
        String name = "morpheus";
        String job = "leader";
        String requestBody = "{\"name\": \"" + name + "\", \"job\": \"" + job + "\"}";
        given()
                .log().all()
                .body(requestBody)
                .contentType(JSON)
                .when()
                .put("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("job", is(job))
                .body("name", is(name));
    }

    @Test
    void getSingleUserNotFountTest() {

        given()
                .log().all()
                .when()
                .get("/users/23")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    void postLoginUnsuccessfulTest() {
        String email = "peter@klaven";
        String requestBody = "{\"email\": \"" + email + "\"}";
        given()
                .log().all()
                .body(requestBody)
                .contentType(JSON)
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}
