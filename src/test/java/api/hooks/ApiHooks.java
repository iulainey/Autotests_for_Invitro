package api.hooks;

import config.ApiConfig;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;

import static io.restassured.RestAssured.*;

public class ApiHooks {

    @Before("@api")
    public void setup() {
        baseURI = ApiConfig.baseUrl();

        requestSpecification = new RequestSpecBuilder()
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Accept", "application/json")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build();

        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}