package userInterface.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage implements Pages{

    WebDriver webDriver;

    @FindBy(xpath = "//img[@alt='Перфоманс Лаб логотип']")
    private WebElement mainPageHeader;

    @FindBy(xpath = "//li[@id='mega-menu-item-24182']/a[text()='Блог']")
    private WebElement blogButton;

    public HomePage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isNewsHeadDisplayed(){
        return mainPageHeader.isDisplayed();
    }

    public void blogButtonClick(){
        blogButton.click();
    }
}
