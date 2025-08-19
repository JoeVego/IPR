package userInterface;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import userInterface.pages.BlogPage;
import userInterface.pages.Pages;
import userInterface.webdriver.Driver;
import userInterface.pages.HomePage;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import static props.ReadProperties.getProperty;

@Epic("Ю Ай тесты")
@Feature("Сайт перфа")
public class MotoZipTests {

    private WebDriver webDriver;
    private HomePage homePage;
    private BlogPage blogPage;

    @BeforeEach
    void init(){
        webDriver = new Driver().getDriver();
        homePage = new HomePage(webDriver);
        blogPage = new BlogPage(webDriver);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        Allure.step("tests initialized");
    }

    @AfterEach
    public void driverTearDown(){
        webDriver.quit();
        Allure.step("driver closed successfully");
    }
    @Tag("UI")
    @Tag("Blog")
    @Description("Проверка поиска в блоге")
    @Owner("Lupa")
    @DisplayName("Проверка поиска в блоге")
    @Test
    public void testBlogSearch() {
        initCheck(webDriver);
        initCheck(homePage);
        initCheck(blogPage);

        try{
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            homePage.blogButtonClick();
            Assertions.assertTrue(blogPage.isHeaderDisplayed());
            Allure.step("Открылась страница блога");

            blogPage.pickAutomatization();
            Allure.step("В списке выбрана Автоматизация");
            Assertions.assertTrue(blogPage.isAtTagVisible());
            Allure.step("Тэг автоматизации отображается в первой карточке поиска");

        }
        catch (AssertionFailedError | NullPointerException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            //чтобы тест успешным не отметилс в аллюре
            throw new AssertionFailedError();
        }
    }

    /**
     * Спонтанный метод, тесты падали из-за кривых рук моих нуллОбъектов
     * добавил проверку
     * а потом решил оставить и вынести в отдельный метод
     * @param obj объект для проверки
     */
    private void initCheck(Object obj){
        try {
            Assertions.assertNotNull(obj);
        }
        catch (AssertionFailedError assertExc){
            Allure.step("AT start failed");
        }
    }

    private void takeScreenshot() {
        byte[] src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("screen", new ByteArrayInputStream(src));
    }
}
