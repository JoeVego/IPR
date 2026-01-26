package userInterface;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.opentest4j.AssertionFailedError;

import userInterface.pages.*;
import userInterface.webdriver.Driver;

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
    void init(){
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
    public void driverTearDown(){
        webDriver.quit();
        Allure.step("driver closed successfully");
    }

    @Tag("UI")
    @Tag("Blog")
    @Description("Проверка поиска в блоге")
    @Owner("Vupsen")
    @DisplayName("Проверка поиска в блоге")
    @Test
    public void testBlogSearch() {

        try{

            Allure.step("Открытие главной страницы", () -> {
                webDriver.get(getProperty("homepage"));
                Assertions.assertTrue(homePage.isNewsHeadDisplayed());
                Allure.step("homePage opened");
            });

            Allure.step("открытие страницы блога", () -> {
                homePage.blogButtonClick();
                Assertions.assertTrue(blogPage.isHeaderDisplayed());
                Allure.step("нажали на кнопку блога");
            });

            Allure.step("проверка выбора тематики АТ", () -> {
                blogPage.pickAutomatization();
                Allure.step("В списке выбрана Автоматизация");
                Assertions.assertTrue(blogPage.isAtTagVisible());
                Allure.step("Тэг автоматизации отображается в первой карточке поиска");
            });
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            throw new AssertionFailedError("тест неуспешен, драйвер закрыт");
        }
    }

    @Tag("UI")
    @Tag("Order")
    @Description("Проверка полей на форме заказа")
    @Owner("Pupsen")
    @DisplayName("Проверка полей на форме заказа")
    @Test
    public void testRequiredFieldsCheck() {

        try{
            Allure.step("Открытие главной страницы", () -> {
                webDriver.get(getProperty("homepage"));
                Assertions.assertTrue(homePage.isNewsHeadDisplayed());
                Allure.step("homePage opened");
            });

            Allure.step("Открытие окна заказа", () -> {
                homePage.orderButtonClick();
                Assertions.assertTrue(orderWindow.isOrderHeaderDisplayed());
                Allure.step("нажали кнопку заказа");
            });

            Allure.step("Выбрать согласие", () -> {
                orderWindow.setAgreementCheckboxTrue();
                Allure.step("чекбокс согласия установлен");
            });

            Allure.step("Проверка подсказок", () -> {
                Allure.step("Проверка емаил", () -> {
                    Assertions.assertTrue(orderWindow.isEmailFiledTipDisplayed());
                    Allure.step("емаил на месте");
                });
                Allure.step("Проверка имени", () -> {
                    Assertions.assertTrue(orderWindow.isNameFiledTipDisplayed());
                    Allure.step("имя на месте");
                });
                Allure.step("Проверка орг", () -> {
                    Assertions.assertTrue(orderWindow.isOrgFiledTipDisplayed());
                    Allure.step("орг на месте");
                });
                Allure.step("Проверка телефона", () -> {
                    Assertions.assertTrue(orderWindow.isPhoneFiledTipDisplayed());
                    Allure.step("телефон на месте");
                });
                Allure.step("все подсказки присутствуют");
            });

            Allure.step("Главная подсказка на месте", () -> {
                orderWindow.sendButtonClick();
                Assertions.assertTrue(orderWindow.isMainTipDisplayed());
                Allure.step("Основная проверка присутствует");
            });
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            throw new AssertionFailedError("тест неуспешен, драйвер закрыт");
        }
    }

    @Tag("UI")
    @Tag("Vacancy")
    @Description("не тест, а актуальный запрос")
    @Owner("Author")
    @DisplayName("Проверка наличия вакансии АТ")
    @Test
    public void testVacancySearch() {

        try{
            Allure.step("Открытие главной страницы", () -> {
                webDriver.get(getProperty("homepage"));
                Assertions.assertTrue(homePage.isNewsHeadDisplayed());
                Allure.step("homePage opened");
            });

            Allure.step("Открытие страницы вакансий", () -> {
                homePage.careerButtonClick();
                homePage.vacancyButtonClick();
                Assertions.assertTrue(vacancyPage.isVacancyHeaderDisplayed());
                Allure.step("Заголовок вакансий пристуствует");
            });

            Allure.step("А есть ли Ат вакансии ?", () -> {
                Assertions.assertTrue(vacancyPage.isAtVacanciesDisplayed());
                //чтобы скрины были, а то при успехе 0 скринов в отчете)
                takeScreenshot();
                Allure.step("Вакансии Ат присутсвуют в Перфе");
            });
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            throw new AssertionFailedError("тест неуспешен, драйвер закрыт");
        }
    }

    @Tag("UI")
    @Tag("Matreshka")
    @Description("переход на страницу матрешки")
    @Owner("Author")
    @DisplayName("Проверка переход на страницу матрешки")
    @Test
    public void testVkLinks() {

        try{
            Allure.step("Открытие главной страницы", () -> {
                webDriver.get(getProperty("homepage"));
                Assertions.assertTrue(homePage.isNewsHeadDisplayed());
                Allure.step("homePage opened");
            });

            Allure.step("Навигация по меню", () -> {
                homePage.productsButtonClick();
                Allure.step("нажали на Услуги и продукты галвного меню");
                homePage.devopsButtonClick();
                Allure.step("выбрали пнукт Внедрение ДевОпс");
            });

            Allure.step("Переход по ссылке матрешки", () -> {
                JavascriptExecutor jse = (JavascriptExecutor)webDriver;
                jse.executeScript("window.scrollTo(0, 6700);");
                waitInSeconds(5);
                Allure.step("Сделали скролл для подгрузки страницы");
                devopsPage.matreshkaButtonClick();
                Allure.step("Нажали на ссылку матрешки");
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
            });
        }
        //можно заменить классом Exception, что и сделал далее по коду
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            throw new AssertionFailedError("тест неуспешен, драйвер закрыт");
        }
    }

    @Tag("UI")
    @Tag("Cases")
    @Description("поиск по кейсам и их открытие")
    @Owner("Author")
    @DisplayName("Проверка поиска по кейсам и их открытия")
    @Test
    public void testCasesSearch() {

        try{
            Allure.step("Открытие главной страницы", () -> {
                webDriver.get(getProperty("homepage"));
                Assertions.assertTrue(homePage.isNewsHeadDisplayed());
                Allure.step("homePage opened");
            });

            Allure.step("Переход на страницу кейсов", () -> {
                homePage.casesButtonClick();
                Assertions.assertTrue(casePage.isCasesHeaderDisplayed());
                Allure.step("Перешли на страницу кейсов");
            });

            Allure.step("Переход на страницу кейсов", () -> {
                casePage.enterSearchText("диверсифицированной");
                casePage.searchClick();
                Allure.step("Выполнили поиск по кейсам");
            });

            Allure.step("Переход на страницу кейсов", () -> {
                waitInSeconds(3);
                Assertions.assertEquals(1,casePage.checkSearchResults());
                Allure.step("Найдена 1 статья о ЦФТ");
            });
        }
        catch (Exception exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            throw new AssertionFailedError("тест неуспешен, драйвер закрыт");
        }
    }
    @Tag("UI")
    @Tag("Blog")
    @Description("<Непривычно видеть зеленый аллюр отчет ")
    @Owner("Vupsen")
    @DisplayName("Добавил реалистичности")
    @Test
    public void testFailed() {

        try{
            Allure.step("Главное страницу открываем", () -> {
                webDriver.get(getProperty("homepage"));
                Assertions.assertTrue(homePage.isNewsHeadDisplayed());
                Allure.step("homePage opened");
            });

            Allure.step("Але хоп, падаем тут", () -> {
                throw new AssertionFailedError();
            });
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            throw new AssertionFailedError("тест неуспешен, драйвер закрыт");
        }
    }

    private void takeScreenshot() {
        byte[] src = ((TakesScreenshot) webDriver).
                getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("screen", new ByteArrayInputStream(src));
    }

    private void waitInSeconds(int seconds) throws InterruptedException{
        TimeUnit.SECONDS.sleep(seconds);
    }
}
