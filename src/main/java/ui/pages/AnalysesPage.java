package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import config.UiConfig;

public class AnalysesPage extends TestBase {

    private final String path = "/analizes/for-doctors/";

    public AnalysesPage(WebDriver driver) {
        super(driver);
    }

    private final By firstAnalysisAddToCart = By.xpath("(//a[contains(@class,'analysis') and @data-basket-add='Y'])[1]");
    private final By cartLink = By.xpath("//div[@id='headerCartDynamic']//a[contains(@href,'/cart')]");

    public void open() {
        driver.get(UiConfig.baseUrl() + path);
    }

    public ProductInfo getFirstAnalysisInfo() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(firstAnalysisAddToCart));

        String id = btn.getAttribute("data-product-id");
        String name = btn.getAttribute("data-product-name");
        int price = Integer.parseInt(btn.getAttribute("data-product-price").trim());

        return new ProductInfo(id, name, price);
    }

    public void addFirstAnalysisToCart() {
        click(firstAnalysisAddToCart);
    }

    public void goToCart() {
        if (isPresent(cartLink)) {
            click(cartLink);
        } else {
            driver.get(UiConfig.cartUrl());
        }
    }

    public class ProductInfo {
        private final String id;
        private final String name;
        private final int price;

        public ProductInfo(String id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getPrice() {
            return price;
        }
    }
}
