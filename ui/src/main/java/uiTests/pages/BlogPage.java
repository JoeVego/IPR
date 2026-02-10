package uiTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlogPage {

    private final WebDriver webDriver;

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

    @FindBy(css = "div.uc-select-filter__loader")
    private WebElement fkingLoader;

    public BlogPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    @Step("проверка отображения заголовка Блога")
    public boolean isHeaderDisplayed(){
        return blogHeader.isDisplayed();
    }

    @Step("выбор автоматизации в списке категорий поиска")
    public void pickAutomatization(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(fkingLoader));
        searchListAuto.click();
    }

    @Step("проверка отображения тэга АТ")
    public boolean isAtTagVisible(){
        return atTagInSearchRes.isDisplayed();
    }
}
