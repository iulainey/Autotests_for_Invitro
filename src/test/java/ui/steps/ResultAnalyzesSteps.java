package ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import ui.pages.ResultAnalyzesPage;
import ui.context.UiContext;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Results analyzers validation")
public class ResultAnalyzesSteps {
    private ResultAnalyzesPage page() {
        WebDriver driver = UiContext.getDriver();
        return new ResultAnalyzesPage(driver);
    }

    private void assertFieldHighlighted(boolean condition, String fieldName) {
        assertTrue(condition, String.format("%s is not highlighted", fieldName));
    }

    @Given("I open Invitro home page")
    public void openInvitroHomePage() {
        page().openHome();
    }

    @When("I open Results page")
    public void openResultsPage() {
        page().openResultsPageFromHeader();
    }

    @When("I click Find results")
    public void clickFindResults() {
        page().clickFindResults();
    }

    @Then("validation message should be visible")
    public void validationMessageShouldBeVisible() {
        assertTrue(page().isErrorVisible(), "Validation message is not visible");
    }

    @Then("validation message text should be {string}")
    public void validationMessageTextShouldBe(String expected) {
        String actual = page().getValidationErrorText();

        String normalizedActual = actual.replaceAll("\\s+", "");
        String normalizedExpected = expected.replaceAll("\\s+", "");

        assertEquals(normalizedExpected, normalizedActual,
                "Validation text mismatch. Actual: [" + actual + "]");
    }

    @Then("all fields should be highlighted red")
    public void allFieldsShouldBeHighlightedRed() {
        assertFieldHighlighted(page().isOrderNumberHighlighted(), "OrderNumber");
        assertFieldHighlighted(page().isBirthdayHighlighted(), "Birthday");
        assertFieldHighlighted(page().isLastNameHighlighted(), "LastName");
    }

    @When("I fill order number {string}")
    public void fillOrderNumber(String value) {
        page().setOrderNumber(value);
    }

    @When("I fill birthday {string}")
    public void fillBirthday(String value) {
        page().setBirthday(value);
    }

    @When("I fill last name {string}")
    public void fillLastName(String value) {
        page().setLastName(value);
    }

    @Then("order number field should not be highlighted")
    public void orderNumberShouldNotBeHighlighted() {
        assertFalse(page().isOrderNumberHighlighted(), "OrderNumber still highlighted");
    }

    @Then("birthday field should not be highlighted")
    public void birthdayShouldNotBeHighlighted() {
        assertFalse(page().isBirthdayHighlighted(), "Birthday still highlighted");
    }

    @Then("last name field should not be highlighted")
    public void lastNameShouldNotBeHighlighted() {
        assertFalse(page().isLastNameHighlighted(), "LastName still highlighted");
    }

    @Then("validation message should be absent")
    public void validationMessageShouldBeAbsent() {
        assertFalse(page().isErrorVisible(), "Validation message is still visible");
    }
}
