package uiTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//h1[text()='Кейсы']")
    private WebElement casesHeader;

    @FindBy(xpath = "//input[@class='uc-search-filter__input']")
    private WebElement searchField;

    @FindBy(xpath = "//img[@alt='search icon']")
    private WebElement searchIcon;

    public CasePage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("проверка отображения заголовка кейсов")
    public boolean isCasesHeaderDisplayed(){
        return casesHeader.isDisplayed();
    }

    @Step("ввод текста поиска")
    public void enterSearchText(String searchText){
        searchField.sendKeys(searchText);
    }

    @Step("клик по поиску")
    public void searchClick(){
        searchIcon.click();
    }

    @Step("проверка результатов поиска")
    public int checkSearchResults(){
        By selector = By.xpath(
                "//div[@class='" +
                        "uc_post_grid_style_one_item ue_post_grid_item " +
                        "ue-item elementor-animation-grow']");

        return webDriver.findElements(selector).size();
    }

}
