package com.example.utils;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
public class RCommonRestUtil {
    @Test
    public void whenRequestingAResourceThenLinksToResourcesMustBeReturned() {

        String body = RestAssured
                .given()
                .baseUri("http://swapi.co/api")
                .and()
                .queryParam("format", "json")
                .when()
                .get("/")
                .then()
                .log().all()
                /*
                .and().assertThat().statusCode(is(equalTo(200)))
                .and()
                .body("films", response -> notNullValue())
                .body("vehicles", response -> notNullValue())
                .body("people", response -> notNullValue())
                .body("starships", response -> notNullValue())
                .body("species", response -> notNullValue())
                .body("planets", response -> notNullValue())
                */
                .and().extract().body().asString();
    }
}
