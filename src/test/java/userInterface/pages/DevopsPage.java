package userInterface.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DevopsPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//a[text()='IT Matreshka']")
    private WebElement matreshkaButton;

    public DevopsPage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void matreshkaButtonClick(){
        matreshkaButton.click();
    }

}
