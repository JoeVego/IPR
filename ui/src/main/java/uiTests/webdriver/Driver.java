package uiTests.webdriver;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    public Driver() {
        driver = new FirefoxDriver();
        Allure.step("driver initialized");

        driver.manage().window().maximize();
    }

    @Deprecated
    public void driverInit(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    @Deprecated
    public void driverTearDown(){
        driver.quit();
        Allure.step("driver closed");
    }
}
