package ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import ui.pages.DoctorsPage;
import ui.context.UiContext;

@Epic("UI Tests")
@Feature("Doctors page pediatric filter")
public class DoctorsChildSteps {

    private DoctorsPage doctorsPage() {
        return new DoctorsPage(UiContext.getDriver());
    }

    @Given("I open doctors page")
    public void openDoctorsPage() {
        doctorsPage().open();
    }

    @When("I enable pediatric doctors filter")
    public void enablePediatricFilter() {
        doctorsPage().enablePediatricCheckbox();
    }

    @Then("all doctor cards should have child doctor badge")
    public void allCardsShouldHaveBadge() {
        var doctorsWithoutBadge = doctorsPage().getDoctorsWithoutChildBadge();
        Assertions.assertTrue(
                doctorsWithoutBadge.isEmpty(),
                "Found cards without 'Детский врач' badge: " + doctorsWithoutBadge
        );
    }

    @When("I refresh the page")
    public void refreshPage() {
        doctorsPage().refresh();
    }

    @Then("pediatric doctors checkbox should be selected")
    public void checkboxShouldBeSelected() {
        Assertions.assertTrue(doctorsPage().isPediatricCheckboxSelected(),
                "Expected pediatric checkbox to be selected after refresh, but it is NOT selected.");
    }
}
