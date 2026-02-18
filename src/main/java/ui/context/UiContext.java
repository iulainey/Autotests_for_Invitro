package ui.context;

import org.openqa.selenium.WebDriver;

public class UiContext {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) throw new IllegalStateException("WebDriver is null. UiHooks did not run?");
        return driver;
    }

    public static void clear() {
        DRIVER.remove();
    }
}
