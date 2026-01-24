package userInterface.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlogPage {

    WebDriver webDriver;

    @FindBy(xpath = "//h1[@class='elementor-heading-title elementor-size-default']")
    private WebElement blogHeader;

    /**
     * Специально пробовал искпасы на несколько уровней,
     * чтобы проваливаться
     */
    @FindBy(xpath = "//div[@class='uc-select-filter__select-wrapper']" +
            "/select" +
            "/option[@data-title='Автоматизация тестирования']")
    private WebElement searchListAuto;

    @FindBy(xpath = "//div[@class='ue_tags_terms']" +
            "/a[text()='автоматизация тестирования']")
    private WebElement atTagInSearchRes;


    public BlogPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isHeaderDisplayed(){
        return blogHeader.isDisplayed();
    }

    public void pickAutomatization(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(searchListAuto));
        searchListAuto.click();
    }

    public boolean isAtTagVisible(){
        return atTagInSearchRes.isDisplayed();
    }
}
