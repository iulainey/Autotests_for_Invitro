package ui.pages;

import config.UiConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;


public class RadiologyPage extends TestBase {

    private final String path = "/moscow/radiology/";

    public RadiologyPage(WebDriver driver) {
        super(driver);
    }

    private final By secondLevelLinks = By.xpath("//a[contains(@class,'side-bar-second__link')]");
    private final By thirdLevelLinksActiveSecond = By.xpath(
            "//a[contains(@class,'side-bar-second__link') and contains(@class,'active')]" +
                    "/following-sibling::div[contains(@class,'side-bar-third')]" +
                    "//a[contains(@class,'side-bar-third__link')]");
    private final By thirdLevelLinksActiveSecondWithoutMrtBodyXpath = By.xpath(
            "//a[contains(@class,'side-bar-second__link') and contains(@class,'active')]" +
                    "/following-sibling::div[contains(@class,'side-bar-third')]" +
                    "//a[contains(@class,'side-bar-third__link') and not(contains(@href,'mrt_tela'))]");

    public void open() {
        driver.get(UiConfig.baseUrl() + path);
    }

    private List<String> getHrefs(By locator) {
        List<String> result = new ArrayList<>();

        for (WebElement el : driver.findElements(locator)) {
            String href = el.getAttribute("href");
            if (href != null && !href.isBlank()) {
                result.add(href);
            }
        }
        return result;
    }

    public void clickAllMenuItems() {
        open();
        List<String> second = getHrefs(secondLevelLinks);
        for (String secondHref : second) {
            driver.get(secondHref);
            List<String> third = getHrefs(thirdLevelLinksActiveSecond);
            for (String thirdHref : third) {
                driver.get(thirdHref);
            }
        }
    }

    //Исключение в XPath
    public void clickExcludeByXpath() {
        open();
        List<String> second = getHrefs(secondLevelLinks);

        for (String secondHref : second) {
            driver.get(secondHref);

            List<String> third = getHrefs(thirdLevelLinksActiveSecondWithoutMrtBodyXpath);

            for (String thirdHref : third) {
                driver.get(thirdHref);
            }
        }
    }

    //Исключение из коллекции
    public void clickExcludeFromCollection() {
        open();
        List<String> second = getHrefs(secondLevelLinks);

        for (String secondHref : second) {
            driver.get(secondHref);

            List<String> third = getHrefs(thirdLevelLinksActiveSecond);

            third.removeIf(href -> href.contains("mrt_tela"));

            for (String thirdHref : third) {
                driver.get(thirdHref);
            }
        }
    }

    // Исключение в процессе клика (в цикле)
    public void clickExcludeInLoop() {
        open();
        List<String> second = getHrefs(secondLevelLinks);

        for (String secondHref : second) {
            driver.get(secondHref);

            List<String> third = getHrefs(thirdLevelLinksActiveSecond);

            for (String thirdHref : third) {

                if (thirdHref.contains("mrt_tela")) {
                    continue;
                }

                driver.get(thirdHref);
            }
        }
    }

}
