package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
public class TestBase {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected TestBase(WebDriver driver) {
        this(driver, 10);
    }

    protected TestBase(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    protected WebElement el(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> els(By locator) {
        return driver.findElements(locator);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        WebElement e = el(locator);
        e.click();
        e.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        e.sendKeys(Keys.DELETE);
        e.sendKeys(value);
    }

    protected String text(By locator) {
        return el(locator).getText().trim();
    }

    protected boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}

