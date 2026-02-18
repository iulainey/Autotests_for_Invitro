package ui.pages;

import config.UiConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultAnalyzesPage extends TestBase {

    public ResultAnalyzesPage(WebDriver driver) {
        super(driver);
    }

    private final By resultsLink = By.xpath("//a[contains(@class,'invitro_header-get_result')]");
    private final By orderNumberInput = By.xpath("//input[@name='orderNumber']");
    private final By birthdayInput = By.xpath("//input[@name='birthday']");
    private final By lastNameInput = By.xpath("//input[@name='lastName']");
    private final By findResultsButton = By.xpath("//button[normalize-space()='Найти результаты']");
    private final By validationError = By.xpath("//div[contains(@class,'UnauthResultsPage_error')]");

    public void openHome() {
        driver.get(UiConfig.baseUrl());
    }

    public void openResultsPageFromHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(resultsLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
    }

    public void clickFindResults() {
        wait.until(ExpectedConditions.elementToBeClickable(findResultsButton)).click();
    }

    public void setOrderNumber(String value) {
        type(orderNumberInput, value);
    }

    public void setBirthday(String value) {
        type(birthdayInput, value);
        driver.findElement(birthdayInput).sendKeys(Keys.TAB);
    }

    public void setLastName(String value) {
        type(lastNameInput, value);
    }

    public boolean isErrorVisible() {
        return !driver.findElements(validationError).isEmpty();
    }

    public String getValidationErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(validationError))
                .getText()
                .trim();
    }

    public boolean isFieldHighlighted(By locator) {
        String classes = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("class");
        return classes != null && classes.contains("Input_error");
    }

    public boolean isOrderNumberHighlighted() {
        return isFieldHighlighted(orderNumberInput);
    }

    public boolean isBirthdayHighlighted() {
        return isFieldHighlighted(birthdayInput);
    }

    public boolean isLastNameHighlighted() {
        return isFieldHighlighted(lastNameInput);
    }
}
