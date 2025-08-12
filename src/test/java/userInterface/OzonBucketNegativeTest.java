package userInterface;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import props.ReadProperties;
import userInterface.pages.MainPage;
import userInterface.webdriver.Driver;


public class OzonBucketNegativeTest{
    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement bucketIcon;

    @Test
    public void mainTest() {
        Driver ds = new Driver();
        ds.driverInit();

        ds.getDriver().get("https://www.megazip.ru/");
        System.out.println("println");

        bucketIcon.click();
        System.out.println(bucketIcon.getText());


        ds.driverTearDown();
    }
}
