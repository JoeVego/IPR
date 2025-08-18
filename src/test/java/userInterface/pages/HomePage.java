package userInterface.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage implements Pages{

    WebDriver webDriver;

    private By secHeader2 = By.xpath("//img[@alt=\"Перфоманс Лаб логотип\"]");

    public HomePage(WebDriver driver) {
        webDriver = driver;
    }

    public boolean isNewsHeadDisplayed(){
        Assertions.assertNotNull(secHeader2);
        WebElement someEl = webDriver.findElement(secHeader2);

        return someEl.isDisplayed();
    }

}
