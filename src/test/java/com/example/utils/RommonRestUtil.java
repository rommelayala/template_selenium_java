package com.example.utils;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.core.Is.is;

public class RommonRestUtil {
  @Test
  public void whenRequestingAResourceThenLinksToResourcesMustBeReturned() {

    String body =
        RestAssured.given()
            .baseUri("https://rickandmortyapi.com/api/character/768")
            .and()
            .queryParam("format", "json")
            .when()
            .get("/")
            .then()
            .log()
            .all()
            .and()
            .assertThat()
            .statusCode(200)
            .and()
            .extract()
            .body()
            .asString();
    System.out.println("--------------------------------------");
    // Extraer el ID usando JsonPath
    JsonPath jsonPath = new JsonPath(body);
    String id = jsonPath.getString("id");

    System.out.println("--------------------------------------");
    System.out.println("El ID es: " + id);
    System.out.println("El body completo es: " + body);
  }

  @Test
  public void whenRequestingAResourceThenSchemaValidationShouldPass() {
    RestAssured.given()
        .baseUri("https://rickandmortyapi.com/api/character/768")
        .queryParam("format", "json")
        .when()
        .get("/")
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(200)
        // Validación del schema
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/character-schema.json"));
  }

  @Test
  public void validateCharacterDetails() {
    String response =
        RestAssured.given()
            .baseUri("https://rickandmortyapi.com/api/character/768")
            .when()
            .get("/")
            .then()
            .extract()
            .body()
            .asString();

    JsonPath jsonPath = new JsonPath(response);

    // Validaciones adicionales específicas
    Assert.assertTrue("ID debe ser positivo", jsonPath.getInt("id") > 0);
    Assert.assertTrue("Nombre no debe estar vacío", !jsonPath.getString("name").isEmpty());
    Assert.assertTrue(
        "URL de imagen debe terminar en .jpeg", jsonPath.getString("image").endsWith(".jpeg"));
    Assert.assertTrue(
        "La fecha de creación debe ser válida",
        jsonPath.getString("created").matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z"));

    // Validación de la estructura del objeto origin
    Assert.assertNotNull("Origin no debe ser null", jsonPath.get("origin"));
    Assert.assertNotNull("Origin.name no debe ser null", jsonPath.get("origin.name"));
    Assert.assertNotNull("Origin.url no debe ser null", jsonPath.get("origin.url"));

    // Validación de los episodios
    List<String> episodes = jsonPath.getList("episode");
    Assert.assertFalse("La lista de episodios no debe estar vacía", episodes.isEmpty());
    episodes.forEach(
        episode ->
            Assert.assertTrue(
                "URL de episodio debe ser válida",
                episode.startsWith("https://rickandmortyapi.com/api/episode/")));
  }
  /**
   hasKey() verifica la existencia de un campo
   notNullValue() verifica que el campo existe y no es null
   equalTo() verifica el valor específico
   Puedes anidar campos usando punto (.) en la ruta del campo
   Puedes combinar múltiples validaciones usando allOf()
   * */
  @Test
  public void validateLocationField() {
    RestAssured
            .given()
            .baseUri("https://rickandmortyapi.com/api/character/768")
            .when()
            .get()
            .then()
            .assertThat()
            // Validación de la estructura
            .body("location", notNullValue())
            .body("location", instanceOf(Map.class))
            .body("location", allOf(
                    hasKey("name"),
                    hasKey("url")
            ))
            // Validación de los valores
            .body("location.name", equalTo("Earth (Replacement Dimension)"))
            .body("location.url", equalTo("https://rickandmortyapi.com/api/location/20"))
            // Validación del formato de la URL
            .body("location.url", matchesPattern("https://.*api/location/\\d+"));
  }
}
