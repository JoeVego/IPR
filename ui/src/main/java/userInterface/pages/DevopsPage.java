package userInterface.pages;

import io.qameta.allure.Step;
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

    @Step("клик по кнопке перехода в матрешку")
    public void matreshkaButtonClick(){
        matreshkaButton.click();
    }

}
