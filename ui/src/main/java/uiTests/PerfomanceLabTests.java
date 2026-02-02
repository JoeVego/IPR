package uiTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.opentest4j.AssertionFailedError;

import uiTests.pages.*;
import uiTests.webdriver.Driver;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static props.ReadProperties.getProperty;

@Epic("Ю Ай тесты")
@Feature("Сайт перфа")
public class PerfomanceLabTests {

    private WebDriver webDriver;
    private HomePage homePage;
    private BlogPage blogPage;
    private OrderWindow orderWindow;
    private VacancyPage vacancyPage;
    private DevopsPage devopsPage;
    private CasePage casePage;

    @BeforeEach
    void init() {
        webDriver = new Driver().getDriver();
        homePage = new HomePage(webDriver);
        blogPage = new BlogPage(webDriver);
        orderWindow = new OrderWindow(webDriver);
        vacancyPage = new VacancyPage(webDriver);
        devopsPage = new DevopsPage(webDriver);
        casePage = new CasePage(webDriver);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        Allure.step("tests initialized");
    }

    @AfterEach
    public void driverTearDown() {
        webDriver.quit();
        Allure.step("driver closed successfully");
    }

    @Tag("UI")
    @Tag("Blog")
    @Description("Проверка поиска в блоге")
    @Owner("Vupsen")
    @DisplayName("Проверка поиска в блоге")
    @ExtendWith(CustomException.class)
    @Test
    public void testBlogSearch() {
        Allure.step("Открытие главной страницы", () -> {
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            takeScreenshot();
        });

        Allure.step("открытие страницы блога", () -> {
            homePage.blogButtonClick();
            Assertions.assertTrue(blogPage.isHeaderDisplayed());
            takeScreenshot();
        });

        Allure.step("проверка выбора тематики АТ", () -> {
            blogPage.pickAutomatization();
            Assertions.assertTrue(blogPage.isAtTagVisible());
            takeScreenshot();
        });
    }

    @Tag("UI")
    @Tag("Order")
    @Description("Проверка полей на форме заказа")
    @Owner("Pupsen")
    @DisplayName("Проверка полей на форме заказа")
    @ExtendWith(CustomException.class)
    @Test
    public void testRequiredFieldsCheck() {
        Allure.step("Открытие главной страницы", () -> {
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            takeScreenshot();
        });

        Allure.step("Открытие окна заказа", () -> {
            homePage.orderButtonClick();
            Assertions.assertTrue(orderWindow.isOrderHeaderDisplayed());
            takeScreenshot();
        });

        Allure.step("Выбрать согласие", () -> {
            orderWindow.setAgreementCheckboxTrue();
            takeScreenshot();
        });

        Allure.step("Проверка подсказок", () -> {
            orderWindow.isAdditionalTipsDisplayed();
            takeScreenshot();
        });

        Allure.step("Главная подсказка на месте", () -> {
            orderWindow.sendButtonClick();
            Assertions.assertTrue(orderWindow.isMainTipDisplayed());
            takeScreenshot();
        });
    }

    @Tag("UI")
    @Tag("Vacancy")
    @Description("не тест, а актуальный запрос")
    @Owner("Author")
    @DisplayName("Проверка наличия вакансии АТ")
    @ExtendWith(CustomException.class)
    @Test
    public void testVacancySearch() {
        Allure.step("Открытие главной страницы", () -> {
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            takeScreenshot();
        });

        Allure.step("Открытие страницы вакансий", () -> {
            homePage.careerButtonClick();
            homePage.vacancyButtonClick();
            Assertions.assertTrue(vacancyPage.isVacancyHeaderDisplayed());
        });

        Allure.step("А есть ли Ат вакансии ?", () -> {
            Assertions.assertTrue(vacancyPage.isAtVacanciesDisplayed());
            takeScreenshot();
        });
    }

    @Tag("UI")
    @Tag("Matreshka")
    @Description("переход на страницу матрешки")
    @Owner("Author")
    @DisplayName("Проверка переход на страницу матрешки")
    @ExtendWith(CustomException.class)
    @Test
    public void testVkLinks() {
        Allure.step("Открытие главной страницы", () -> {
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            takeScreenshot();
        });

        Allure.step("Навигация по меню", () -> {
            homePage.productsButtonClick();
            homePage.devopsButtonClick();
            takeScreenshot();
        });

        Allure.step("Переход по ссылке матрешки", () -> {
            JavascriptExecutor jse = (JavascriptExecutor) webDriver;
            jse.executeScript("window.scrollTo(0, 6700);");
            waitInSeconds(5);
            Allure.step("Сделали скролл для подгрузки страницы");

            devopsPage.matreshkaButtonClick();
            takeScreenshot();
        });

        Allure.step("Переключились на страницу матрешки", () -> {
            ArrayList<String> tabs2 = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs2.get(1));
        });

        Allure.step("Проверка переключения на ссылку матрешки", () -> {
            waitInSeconds(10);
            Assertions.assertEquals(webDriver.getCurrentUrl(),
                    "https://www.performance-lab.ru/itmatreshka/");
            Allure.step("Страница матрешки открылась");
            takeScreenshot();
        });
    }

    @Tag("UI")
    @Tag("Cases")
    @Description("поиск по кейсам и их открытие")
    @Owner("Author")
    @DisplayName("Проверка поиска по кейсам и их открытия")
    @ExtendWith(CustomException.class)
    @Test
    public void testCasesSearch() {

        Allure.step("Открытие главной страницы", () -> {
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            takeScreenshot();
        });

        Allure.step("Переход на страницу кейсов", () -> {
            homePage.casesButtonClick();
            Assertions.assertTrue(casePage.isCasesHeaderDisplayed());
            takeScreenshot();
        });

        Allure.step("Переход на страницу кейсов", () -> {
            casePage.enterSearchText("диверсифицированной");
            casePage.searchClick();
            takeScreenshot();
        });

        Allure.step("Переход на страницу кейсов", () -> {
            waitInSeconds(3);
            Assertions.assertEquals(1, casePage.checkSearchResults());
            Allure.step("Найдена 1 статья о ЦФТ");
            takeScreenshot();
        });
    }

    @Tag("UI")
    @Tag("Blog")
    @Description("<Непривычно видеть зеленый аллюр отчет ")
    @Owner("Vupsen")
    @DisplayName("Добавил реалистичности")
    @ExtendWith(CustomException.class)
    @Test
    public void testFailed() {
        Allure.step("Главное страницу открываем", () -> {
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            takeScreenshot();
        });

        Allure.step("Але хоп, падаем тут", () -> {
            throw new AssertionFailedError();
        });
    }

    private void takeScreenshot() {
        byte[] src = ((TakesScreenshot) webDriver).
                getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("screen", new ByteArrayInputStream(src));
    }

    private void waitInSeconds(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
}
