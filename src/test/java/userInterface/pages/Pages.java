package userInterface.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {
    public WebDriver driver;

    public Pages(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}