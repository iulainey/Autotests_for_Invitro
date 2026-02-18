package ui.pages;

import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorsPage extends TestBase{

    private final String url = "https://www.invitro.ru/moscow/vrachi/";

    public DoctorsPage(WebDriver driver) {
        super(driver);
    }

    private final By pediatricCheckbox = By.xpath("//input[@id='checkbox_docchild']");
    private final By doctorCards = By.xpath("//div[contains(@class,'vrach-card') and contains(@class,'vrachi_list__item')]");
    private final By badgeChildDoctor = By.xpath(".//span[contains(@class,'vrach-card__job-main-title--green')]");
    private final By doctorName = By.xpath(".//a[contains(@class,'vrach-card__person-name')]");

    public void open() {
        driver.get(url);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public boolean isPediatricCheckboxSelected() {
        return driver.findElement(pediatricCheckbox).isSelected();
    }

    public void enablePediatricCheckbox() {
        WebElement checkbox = driver.findElement(pediatricCheckbox);
        if (!checkbox.isSelected()) {
            driver.findElement(By.xpath("//label[@for='checkbox_docchild']")).click();
        }
    }

    public List<WebElement> getDoctorCards() {
        return driver.findElements(doctorCards);
    }

    public List<String> getDoctorsWithoutChildBadge() {
        List<WebElement> cards = getDoctorCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("No doctor cards found on the page");
        }

        List<String> badDoctors = new ArrayList<>();

        for (WebElement card : cards) {
            boolean hasBadge = !card.findElements(badgeChildDoctor).isEmpty();
            if (!hasBadge) {
                String name = card.findElements(doctorName).isEmpty()
                        ? "<no name>"
                        : card.findElement(doctorName).getText();

                badDoctors.add(name);
            }
        }
        return badDoctors;
    }
}

