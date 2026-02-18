package ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.PriceUtils;

public class CartPage extends TestBase {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By productLiByName(String productName) {
        return By.xpath(
                "//div[contains(@class,'CartProductsPanel_productList')]//li" +
                        "[.//button[contains(@class,'CartProduct_productName') and normalize-space(.)=\"" + productName + "\"]" +
                        " or .//span[contains(@class,'CartProduct_productName') and normalize-space(.)=\"" + productName + "\"]]"
        );
    }

    private final String productLiXpath =
            "//div[contains(@class,'CartProductsPanel_productList')]//li" +
                    "[.//button[contains(@class,'CartProduct_productName') and normalize-space(.)='%1$s']" +
                    " or .//span[contains(@class,'CartProduct_productName') and normalize-space(.)='%1$s']]";
    private final By priceInLi = By.xpath(".//div[contains(@class,'CartProduct_productPrice')]//span");

    public int getProductPriceInCartByName(String productName) {

        By productLi = By.xpath(String.format(productLiXpath, productName));

        WebElement li = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productLi)
        );

        String text = li.findElement(priceInLi).getText();

        return PriceUtils.parsePrice(text);
    }

    private int parsePrice(String text) {
        String digits = text.replace('\u00A0', ' ').replaceAll("[^0-9]", "");
        if (digits.isBlank()) {
            throw new IllegalStateException("Price text is empty! Raw text: [" + text + "]");
        }
        return Integer.parseInt(digits);
    }
}