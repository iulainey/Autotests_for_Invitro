package ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import ui.pages.CartPage;
import ui.pages.AnalysesPage;
import ui.context.CartContext;
import ui.context.UiContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("UI Tests")
@Feature("Cart price validation")
public class PriceSteps {

    private WebDriver driver() {
        return UiContext.getDriver();
    }

    @Given("I open analyses for doctors page")
    public void openAnalyses() {
        new AnalysesPage(driver()).open();
    }

    @When("I remember analysis price and add it to cart")
    public void rememberAndAdd() {
        AnalysesPage page = new AnalysesPage(driver());
        AnalysesPage.ProductInfo info = page.getFirstAnalysisInfo();
        CartContext.rememberProduct(
                info.getId(),
                info.getName(),
                info.getPrice()
        );
        page.addFirstAnalysisToCart();
        assertNotNull(CartContext.productPrice(), "Price was not remembered");
    }

    @When("I go to cart")
    public void goToCart() {
        new AnalysesPage(driver()).goToCart();
    }

    @Then("product price in cart should equal remembered price")
    public void comparePrices() {
        int expected = CartContext.productPrice();
        int actual = new CartPage(driver()).getProductPriceInCartByName(CartContext.productName());
        assertEquals(expected, actual, "Price mismatch for product: " + CartContext.productName());
    }
}
