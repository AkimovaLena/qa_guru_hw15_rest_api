package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class CreateSpec {
    public static RequestSpecification createRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification responseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseSpec404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();

}
