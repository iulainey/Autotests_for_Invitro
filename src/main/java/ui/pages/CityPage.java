package ui.pages;

import config.UiConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CityPage extends TestBase {

    public CityPage(WebDriver driver) {
        super(driver);
    }

    private final By cityContainer = By.id("city");
    private final By chooseAnotherBtn = By.xpath("//a[contains(@class,'city__change-btn')]");
    private final By citySearchInput = By.xpath("//input[contains(@placeholder,'Поиск')]");
    private final By currentCityText = By.xpath("//*[@id='city']//span[contains(@class,'city__name--main') or contains(@class,'city__name--label')]");
    private final By suggestContainer = By.xpath("//div[contains(@class,'easy-autocomplete-container')]//ul");
    private final String cityOptionTemplate =
            "//div[contains(@class,'easy-autocomplete-container')]" +
                    "//div[contains(@class,'eac-item')]" +
                    "[normalize-space(.)='%1$s' or .//b[normalize-space(.)='%1$s']]";

    public void open() {
        driver.get(UiConfig.baseUrl());
    }

    public void openCityDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(cityContainer)).click();
    }

    public void clickChooseAnother() {
        wait.until(ExpectedConditions.elementToBeClickable(chooseAnotherBtn)).click();
    }

    public void searchCity(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(citySearchInput));
        input.clear();
        input.sendKeys(text);
    }

    public void chooseCityFromSuggest(String cityRu) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestContainer));
        By cityOption = By.xpath(String.format(cityOptionTemplate, cityRu));
        wait.until(ExpectedConditions.elementToBeClickable(cityOption)).click();
    }

    public String getCurrentCityText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentCityText))
                .getText().trim();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}