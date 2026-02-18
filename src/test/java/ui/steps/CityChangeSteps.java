package ui.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import ui.pages.CityPage;
import ui.context.UiContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Tests")
@Feature("City change")
public class CityChangeSteps {

    private CityPage page() {
        WebDriver driver = UiContext.getDriver();
        return new CityPage(driver);
    }

    @When("I change city to {string}")
    public void changeCityTo(String cityRu) {
        page().open();
        page().openCityDropdown();
        page().clickChooseAnother();
        page().searchCity(cityRu);
        page().chooseCityFromSuggest(cityRu);
    }

    @Then("current city should be {string}")
    public void currentCityShouldBe(String expectedCityRu) {
        assertEquals(expectedCityRu, page().getCurrentCityText());
    }

    @Then("URL should contain {string}")
    public void urlShouldContain(String slug) {
        assertTrue(page().getUrl().toLowerCase().contains(slug.toLowerCase()));
    }
}