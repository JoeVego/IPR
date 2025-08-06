package userInterface;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import props.ReadProperties;
import userInterface.webdriver.Driver;


public class OzonBucketNegativeTest{

//    @Test
    public void mainTest() {
        Driver ds = new Driver();
        ds.driverInit();
        ds.getDriver().get("https://www.ozon.ru/");
        System.out.println("println");
        ds.driverTearDown();
    }
}
