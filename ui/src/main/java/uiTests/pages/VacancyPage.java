package uiTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VacancyPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//h1[text()='Открытые вакансии']")
    private WebElement vacancyHeader;

    public VacancyPage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("отображается ли заголовок вакансий")
    public boolean isVacancyHeaderDisplayed(){
        return vacancyHeader.isDisplayed();
    }

    @Step("есть ли вакансии АТ")
    public boolean isAtVacanciesDisplayed(){
        By selector = By.xpath("//div[@class='uc_post_list_box']");
        List<WebElement> listOfElements = webDriver.findElements(selector);

        int numOfAtVacancies = (int)listOfElements
                .stream()
                .filter(webElement -> webElement.getText().contains("автоматиз")
                | webElement.getText().contains("Automation"))
                .count();

        return numOfAtVacancies > 0;
    }
}
