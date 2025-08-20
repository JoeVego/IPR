package userInterface.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderWindow {

    private WebDriver webDriver;

    @FindBy(xpath = "//h4[text()='Свяжитесь с нами']")
    private WebElement orderHeader;

    @FindBy(xpath = "//label/span[@data-name='your-name']" +
            "/span[@class='wpcf7-not-valid-tip']")
    private WebElement nameFiledTip;

    @FindBy(xpath = "//label/span[@data-name='your-email']" +
            "/span[@class='wpcf7-not-valid-tip']")
    private WebElement emailFieldTip;

    @FindBy(xpath = "//label/span[@data-name='your-tel']" +
            "/span[@class='wpcf7-not-valid-tip']")
    private WebElement phoneFieldTip;

    @FindBy(xpath = "//label/span[@data-name='your-organization']" +
            "/span[@class='wpcf7-not-valid-tip']")
    private WebElement organizationFieldTip;

    @FindBy(xpath = "//input[@name='acceptance-202']")
    private WebElement agreementCheckBox;

    @FindBy(xpath = "//div[@id='order-testing-modal']" +
            "//input[@value='Отправить']")
    private WebElement sendButton;

    @FindBy(xpath = "//div[@id='order-testing-modal']" +
            "//div[@class='wpcf7-response-output']")
    private WebElement mainTipOfFields;

    public OrderWindow(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOrderHeaderDisplayed(){
        return orderHeader.isDisplayed();
    }

    public boolean isNameFiledTipDisplayed(){
        return nameFiledTip.isDisplayed();
    }

    public boolean isEmailFiledTipDisplayed(){
        return emailFieldTip.isDisplayed();
    }

    public boolean isPhoneFiledTipDisplayed(){
        return phoneFieldTip.isDisplayed();
    }

    public boolean isOrgFiledTipDisplayed(){
        return organizationFieldTip.isDisplayed();
    }

    public void setAgreementCheckboxTrue(){
        agreementCheckBox.click();
    };

    public void sendButtonClick(){
        sendButton.click();
    }

    public boolean isMainTipDisplayed(){
        return mainTipOfFields.isDisplayed();
    }


}
