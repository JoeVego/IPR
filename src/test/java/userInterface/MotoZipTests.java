package userInterface;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

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

    @BeforeEach
    void init(){
        webDriver = new Driver().getDriver();
        homePage = new HomePage(webDriver);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        Allure.step("tests initialized");

        Assertions.assertNotNull(homePage);
    }

    @AfterEach
    public void driverTearDown(){
        webDriver.quit();
        Allure.step("driver closed successfully");
    }

    @Test
    public void testHappyHoursIsActive() {
        initCheck(webDriver, homePage);

        Assertions.assertNotNull(homePage);

        try{
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");
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
     * можно и удалить
     * @param driverObj объект веб райвера
     * @param pageObj объект страницы
     */
    private void initCheck(WebDriver driverObj, Pages pageObj){
        try {
            Assertions.assertNotNull(webDriver);
            Assertions.assertNotNull(homePage);
        }
        catch (AssertionFailedError assertExc){
            Allure.step("Init failed");
        }
    }

    private void takeScreenshot() {
        byte[] src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("screen", new ByteArrayInputStream(src));
    }
}
