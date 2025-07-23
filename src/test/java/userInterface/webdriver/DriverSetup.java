package userInterface.webdriver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSetup {
    private org.openqa.selenium.WebDriver driver;
    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(DriverSetup.class);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public  void driverInit(){
        //Чтобы сделать запись в лог, можно использовать множество методов, которые показывают, с каким уровнем будут записи. Например:
        //тут лог уровня инфо с текстом
        logger.info("- - - - - - - -Перед созданием драйвера- - - - - - - -");

        //определение пути до драйвера
        System.setProperty("webdriver.gecko.driver", TestProperties.getProperty("firefoxdriver"));
        logger.info("- - - - - - - -Чтение пути до драйвера- - - - - - - -");
        //создание экземпляра драйвера: для запуска браузера необходимо создать объект драйвера
        driver = new FirefoxDriver();
        logger.info("- - - - - - - -Создание экземпляра драйвера- - - - - - - -");
        //окно разворачивается на полный экран, для отображения хода теста
        driver.manage().window().maximize();
        logger.info("- - - - - - - -Развертывание окна- - - - - - - -");
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        logger.info("- - - - - - - -Ожидание прошло {}- - - - - - - -", 30);
        //передача драйверу адреса открываемой страницы из файла настроек
        driver.get(TestProperties.getProperty("homepage"));
        logger.info("- - - - - - - -Передача страницы- - - - - - - -");
    }

    //Сделал аннотацию АфтерИч для теста в родительском классе
    @AfterEach
    public void driverTearDown(){
        logger.info("- - - - - - - -Присутпаем к закрытию драйвера- - - - - - - -");
        //закрытие драйвера
        driver.quit();
        logger.info("- - - - - - Драйвер закрыт- - - - - - - -");
    }
}
