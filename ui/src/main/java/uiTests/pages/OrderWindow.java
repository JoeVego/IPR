package uiTests.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderWindow {

    private final WebDriver webDriver;

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

    @FindBy(xpath = "//button[@data-cky-tag='accept-button']")
    private WebElement cookiesBtn;

    public OrderWindow(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка отображения заголовка заказов")
    public boolean isOrderHeaderDisplayed(){
        return orderHeader.isDisplayed();
    }

    @Step("отображается ли подсказка поля имя")
    public boolean isNameFiledTipDisplayed(){
        return nameFiledTip.isDisplayed();
    }
    @Step("отображается ли подсказка поля емаил")
    public boolean isEmailFiledTipDisplayed(){
        return emailFieldTip.isDisplayed();
    }

    @Step("отображается ли подсказка поля телефон")
    public boolean isPhoneFiledTipDisplayed(){
        return phoneFieldTip.isDisplayed();
    }

    @Step("отображается ли подсказка поля орагнизация")
    public boolean isOrgFiledTipDisplayed(){
        return organizationFieldTip.isDisplayed();
    }

    @Step("установка чек-бокса согласия в True")
    public void setAgreementCheckboxTrue(){
        agreementCheckBox.click();
    }

    @Step("Нажатие кнопки Отправить")
    public void sendButtonClick(){
        sendButton.click();
    }

    @Step("Нажатие кнопки Принять куки")
    public void cookiesButtonClick(){cookiesBtn.click(); }

    @Step("Проверка отображения главной подсказки")
    public boolean isMainTipDisplayed(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(mainTipOfFields));
        return mainTipOfFields.isDisplayed();
    }

    @Step("Проверка отображения дополнительных подсказок")
    public void isAdditionalTipsDisplayed() {
        Allure.step("Проверка подсказки заполнения емаил", () -> Assertions.assertTrue(isEmailFiledTipDisplayed()));
        Allure.step("Проверка подсказки заполнения имени", () -> Assertions.assertTrue(isNameFiledTipDisplayed()));
        Allure.step("Проверка подсказки заполнения орг", () -> Assertions.assertTrue(isOrgFiledTipDisplayed()));
        Allure.step("Проверка подсказки заполнения телефона", () -> Assertions.assertTrue(isPhoneFiledTipDisplayed()));
    }

}
