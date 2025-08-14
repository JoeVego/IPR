package userInterface;

//import org.junit.Test;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.opentest4j.AssertionFailedError;
import userInterface.pages.Pages;
import userInterface.webdriver.Driver;
import userInterface.pages.HomePage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static props.ReadProperties.getProperty;

public class MotoZipTests {

    private WebDriver webDriver;
    private HomePage homePage;

    @BeforeEach
    void init(){
        webDriver = new Driver().getDriver();
        homePage = new HomePage();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Allure.step("tests initialized");
    }

    @AfterEach
    public void driverTearDown(){
        webDriver.quit();
        Allure.step("driver closed successfully");
    }

    @Test
    public void testHappyHoursIsActive() {
        initCheck(webDriver, homePage);

        try{
        webDriver.get(getProperty("homepage"));
        Assertions.assertTrue(homePage.isMainLogoDisplayed());
        Allure.step("homePage opened");

        Assertions.assertTrue(homePage.isSaleTextDisplayed());}
        catch (AssertionFailedError | NullPointerException exc){
            takeScreenshot();

            webDriver.close();
            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
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
        String strPath = "allure-results/image1.jpg";

        byte[] src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("screen", new ByteArrayInputStream(src));
//        return src;
    }
}
