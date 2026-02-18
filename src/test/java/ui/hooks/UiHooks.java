package ui.hooks;

import io.cucumber.java.Scenario;
import ui.utils.AllureAttachments;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.context.CartContext;
import ui.context.UiContext;
import config.UiConfig;

public class UiHooks {

    @Before("@ui")
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        if (UiConfig.headless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        UiContext.setDriver(driver);
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        WebDriver driver = null;
        try {
            driver = UiContext.getDriver();

            if (scenario.isFailed() && driver != null) {
                AllureAttachments.attachScreenshot(driver);
            }

        } catch (Exception ignored) {}

        if (driver != null) {
            driver.quit();
        }

        CartContext.clear();
        UiContext.clear();
    }
}
