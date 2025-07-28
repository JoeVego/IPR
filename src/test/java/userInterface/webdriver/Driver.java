package userInterface.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import props.ReadProperties;

import java.time.Duration;

public class Driver {
    private org.openqa.selenium.WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    /**
     * Запуск драйвера
     */
    public void driverInit(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //передача драйверу адреса открываемой страницы из файла настроек
        driver.get(ReadProperties.getProperty("homepage"));
    }

    public void driverTearDown(){
        System.out.println("closed");
        //закрытие драйвера
        driver.quit();
    }
}
