package userInterface;

//import org.junit.Test;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import userInterface.webdriver.Driver;
import userInterface.pages.HomePage;
import static props.ReadProperties.getProperty;

public class MotoZipTests {

    private WebDriver webDriver;
    private HomePage homePage;

    @BeforeEach
    void init(){
        webDriver = new Driver().getDriver();
        homePage = new HomePage();

        Allure.step("tests initialized");
    }

    @Test
    public void testHappyHoursIsActive() {

        webDriver.get(getProperty("homepage"));
        Assertions.assertTrue(homePage.bucketIconClick());
        Allure.step("homePage opened");

        Assertions.assertTrue(homePage.isSaleTextDisplayed());
    }
}
