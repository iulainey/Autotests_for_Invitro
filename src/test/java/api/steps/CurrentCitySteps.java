package api.steps;

import api.context.ApiContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API Tests")
@Feature("Current city API validation")
public class CurrentCitySteps {

    @When("I request current city with code {string}")
    public void requestCurrentCityWithCode(String code) {
        ApiContext.setResponse(
                given()
                        .log().all()
                        .queryParam("CODE", code)
                        .when()
                        .get("/local/ajax/current-city.php")
                        .then()
                        .extract().response()
        );
    }

    @Then("response status should be {int}")
    public void responseStatusShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, ApiContext.getResponse().statusCode());
    }

    @Then("response json field {string} should match {string}")
    public void responseJsonFieldShouldMatch(String field, String expected) {

        String actual = ApiContext.getResponse()
                .jsonPath()
                .getString(field);

        if ("null".equalsIgnoreCase(expected)) {
            assertNull(actual,
                    "Expected null but was: " + actual +
                            "\nFull response: " + ApiContext.getResponse().asString());
        } else {
            assertEquals(expected, actual,
                    "Mismatch for field '" + field + "'");
        }
    }
}