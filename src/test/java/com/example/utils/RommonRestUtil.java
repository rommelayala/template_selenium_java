package com.example.utils;

import static org.hamcrest.CoreMatchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.Map;

public class RommonRestUtil {
    private RequestSpecification RestRequest;
    private io.restassured.response.Response RestResponse;
    private final Logger logger = LogManager.getLogger("com.privalia.qa.specs.CommonG");
    /**
     * Get the common logger.
     *
     * @return Logger
     */
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Get the previos Rest response (restassured)
     *
     * @return Rest response
     */
    public io.restassured.response.Response getRestResponse() {
        return RestResponse;
    }

    /**
     * Sets the Rest response (restassured)
     *
     * @param restResponse Rest response
     */
    public void setRestResponse(io.restassured.response.Response restResponse) {
        RestResponse = restResponse;
    }
    public RequestSpecification getRestRequest() {
        return this.RestRequest;
    }
    public void generateRestRequest(String requestType, String endPoint) {

        this.getRestRequest().basePath(endPoint);

        this.getLogger().debug("Generating " + requestType + " request to " + endPoint);

        if (this.getLogger().isDebugEnabled()) {
            this.getRestRequest().given().log().all();
        }

        switch (requestType) {
            case "GET":
                this.setRestResponse(this.getRestRequest().when().get());
                break;

            case "POST":
                this.setRestResponse(this.getRestRequest().when().post());
                break;

            case "PUT":
                this.setRestResponse(this.getRestRequest().when().put());
                break;

            case "DELETE":
                this.setRestResponse(this.getRestRequest().when().delete());
                break;

            case "PATCH":
                this.setRestResponse(this.getRestRequest().when().patch());
                break;

            default:
                Assertions.fail("Operation not implemented: " + requestType);

        }

        if (this.getLogger().isDebugEnabled()) {
            this.getRestResponse().then().log().all();
        }

    }
}
