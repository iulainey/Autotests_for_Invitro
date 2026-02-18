package ui.steps;

import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import ui.pages.RadiologyPage;
import ui.context.UiContext;

@Epic("UI Tests")
@Feature("Radiology menu click-through")
public class RadiologyMenuSteps {

    private RadiologyPage page() {
        WebDriver driver = UiContext.getDriver();
        return new RadiologyPage(driver);
    }

    @When("I open Radiology page")
    public void openRadiologyPage() {
        page().open();
    }

    @When("I click all menu items")
    public void clickAllMenuItems() {
        page().clickAllMenuItems();
    }

    @When("I click all menu items excluding MRT body by XPath")
    public void clickExcludeByXpath() {
        page().clickExcludeByXpath();
    }

    @When("I click all menu items excluding MRT body from collection")
    public void clickExcludeFromCollection() {
        page().clickExcludeFromCollection();
    }

    @When("I click all menu items excluding MRT body in loop")
    public void clickExcludeInLoop() {
        page().clickExcludeInLoop();
    }
}
