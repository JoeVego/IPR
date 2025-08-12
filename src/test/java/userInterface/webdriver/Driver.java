package userInterface.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.JavascriptExecutor;
import props.ReadProperties;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {
    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    /**
     * Запуск драйвера
     */
    public void driverInit(){
//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--proxy-server={14.103.38.219:6666}");
//        options.addArguments("--user-agent={Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36}");
//        options.addArguments("--headless");

        driver = new FirefoxDriver();

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
//        System.out.println(js.executeScript("return navigator.userAgent;"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void driverTearDown(){
        System.out.println("closed");
        //закрытие драйвера
        driver.quit();
    }
}
