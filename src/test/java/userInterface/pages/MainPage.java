package userInterface.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement bucketIcon;

    public void bucketIconClick(){
        bucketIcon.click();
    }
}
