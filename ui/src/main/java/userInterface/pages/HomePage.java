package userInterface.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{

    private WebDriver webDriver;

    @FindBy(xpath = "//img[@alt='Перфоманс Лаб логотип']")
    private WebElement mainPageHeader;

    @FindBy(xpath = "//li[@id='mega-menu-item-24182']/a[text()='Блог']")
    private WebElement blogButton;

    //локатор можно укоротить, оставиви только вторую часть,
    // но пробовал искпасы писать по вложенным элементам
    @FindBy(xpath = "//a[@href='#requestOrder']//span[text()='Заказать услугу']")
    private WebElement orderButton;

    @FindBy(xpath = "//li[@id='mega-menu-item-1619']/a")
    private WebElement careerButton;

    @FindBy(xpath = "//a[@href='/vacancy']")
    private WebElement vacancyButton;

    @FindBy(xpath = "//a[text()='Услуги и продукты']")
    private WebElement productsButton;

    @FindBy(xpath = "//a[text()='Внедрение DevOps']")
    private WebElement devopsButton;

    @FindBy(xpath = "//a[text()='Кейсы']")
    private WebElement casesButton;

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

    public void orderButtonClick(){
        orderButton.click();
    }

    public void careerButtonClick(){
        careerButton.click();
    }

    public void vacancyButtonClick(){
        vacancyButton.click();
    }

    public void productsButtonClick(){
        productsButton.click();
    }

    public void devopsButtonClick(){
        devopsButton.click();
    }

    public void casesButtonClick(){casesButton.click();}
}
