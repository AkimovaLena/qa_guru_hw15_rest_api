package tests;

import io.restassured.RestAssured;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateSpec.*;

public class ApiTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void getlistUsersTest() {
        int numberPages = 2;
        ListUsersResponseModel response = step("Make request", () ->
                given(createRequestSpec)
                        .queryParam("page", numberPages)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(ListUsersResponseModel.class));
        step("Check response", () -> {
            assertEquals(response.getPer_page(), 6);
            assertEquals(response.getPage(), numberPages);
            assertEquals(response.getTotal_pages(), (int) Math.ceil((double) response.getTotal() / response.getPer_page()));
            assertEquals(response.getData().size(), response.getPer_page());
        });

    }

    @Test
    void postCreateTest() {

        UserBodyRequestModel requestBody = new UserBodyRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("leader");
        CreateBodyResponseModel response = step("Make request", () ->
                given(createRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec201)
                        .extract().as(CreateBodyResponseModel.class));

        step("Check response", () -> {
            assertEquals(requestBody.getJob(), response.getJob());
            assertEquals(requestBody.getName(), response.getName());
        });
    }

    @Test
    void putUpdateTest() {
        UserBodyRequestModel requestBody = new UserBodyRequestModel();
        requestBody.setName("morpheus");
        requestBody.setJob("leader");
        UpdateBodyResponseModel response = step("Make request", () ->
                given(createRequestSpec)
                        .body(requestBody)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(UpdateBodyResponseModel.class));

        step("Check response", () -> {
            assertEquals(requestBody.getJob(), response.getJob());
            assertEquals(requestBody.getName(), response.getName());
        });
    }

    @Test
    void getSingleUserNotFountTest() {

        step("Make request", () ->
                given(createRequestSpec)
                        .when()
                        .get("/users/23")
                        .then()
                        .spec(responseSpec404)
                        .extract().as(CreateBodyResponseModel.class));

    }

    @Test
    void postLoginUnsuccessfulTest() {
        LoginBodyRequestModel requestBody = new LoginBodyRequestModel();
        requestBody.setEmail("peter@klaven");

        ErrorResponse response = step("Make request", () ->
                given(createRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec400)
                        .extract().as(ErrorResponse.class));

        step("Check response", () -> {
            assertEquals(response.getError(), "Missing password");
        });
    }


}
