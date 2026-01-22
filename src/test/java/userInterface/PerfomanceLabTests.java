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
    @Owner("Vupsen'")
    @DisplayName("Проверка поиска в блоге")
    @Test
    public void testBlogSearch() {

        try{
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            homePage.blogButtonClick();
            Assertions.assertTrue(blogPage.isHeaderDisplayed());
            Allure.step("Открылась страница блога");

            blogPage.pickAutomatization();
            Allure.step("В списке выбрана Автоматизация");
            Assertions.assertTrue(blogPage.isAtTagVisible());
            Allure.step("Тэг автоматизации отображается в первой карточке поиска");
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            throw new AssertionFailedError();
        }
    }

    @Tag("UI")
    @Tag("Order")
    @Description("Проверка полей на форме заказа")
    @Owner("Pupsen'")
    @DisplayName("Проверка полей на форме заказа")
    @Test
    public void testRequiredFieldsCheck() {

        try{
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            homePage.orderButtonClick();
            Assertions.assertTrue(orderWindow.isOrderHeaderDisplayed());
            Allure.step("Открыто окно заказа");

            orderWindow.setAgreementCheckboxTrue();
            Allure.step("чекбокс согласия установлен");

            Allure.step("провека подсказок");
            Assertions.assertTrue(orderWindow.isEmailFiledTipDisplayed());
            Assertions.assertTrue(orderWindow.isNameFiledTipDisplayed());
            Assertions.assertTrue(orderWindow.isOrgFiledTipDisplayed());
            Assertions.assertTrue(orderWindow.isPhoneFiledTipDisplayed());
            Allure.step("все подсказки присутствуют");

            orderWindow.sendButtonClick();
            orderWindow.isMainTipDisplayed();
            Allure.step("Основная проверка присутствует");
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            throw new AssertionFailedError();
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
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            homePage.careerButtonClick();
            homePage.vacancyButtonClick();
            vacancyPage.isVacancyHeaderDisplayed();
            Allure.step("Открыта страница вакансий");

            vacancyPage.isAtVacanciesDisplayed();
            //чтобы скрины были, а то при успехе 0 скринов в отчете)
            takeScreenshot();
            Allure.step("Вакансии Ат присутсвуют в Перфе");
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            throw new AssertionFailedError();
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
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            homePage.productsButtonClick();
            Allure.step("нажали на Услуги и продукты галвного меню");
            homePage.devopsButtonClick();
            Allure.step("выбрали пнукт Внедрение ДевОпс");

            JavascriptExecutor jse = (JavascriptExecutor)webDriver;
            jse.executeScript("window.scrollTo(0, 6700);");
            waitInSeconds(5);
            devopsPage.matreshkaButtonClick();
            Allure.step("Нажали на ссылку матрешки");

            ArrayList<String> tabs2 = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs2.get(1));
            Allure.step("Переключились на страницу матрешки");

            waitInSeconds(10);
            Assertions.assertEquals(webDriver.getCurrentUrl(),
                    "https://www.performance-lab.ru/itmatreshka/");
            Allure.step("Страница матрешки открылась");
        }
        //можно заменить классом Exception, что и сделал далее по коду
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException | InterruptedException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            throw new AssertionFailedError();
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
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            homePage.casesButtonClick();
            Assertions.assertTrue(casePage.isCasesHeaderDisplayed());
            Allure.step("Перешли на страницы кейсов");

            casePage.enterSearchText("диверсифицированной");
            casePage.searchClick();
            Allure.step("Выполнили поиск по кейсам");

            waitInSeconds(3);
            Assertions.assertEquals(1,casePage.checkSearchResults());
            Allure.step("Найдена 1 статья о ЦФТ");
        }
        catch (Exception exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            throw new AssertionFailedError();
        }
    }
    @Tag("UI")
    @Tag("Blog")
    @Description("<Не привычно видеть зеленый аллюр отчет ")
    @Owner("Vupsen'")
    @DisplayName("Добавил реалистичности")
    @Test
    public void testFailed() {

        try{
            webDriver.get(getProperty("homepage"));
            Assertions.assertTrue(homePage.isNewsHeadDisplayed());
            Allure.step("homePage opened");

            throw new AssertionFailedError();
        }
        catch (AssertionFailedError | NullPointerException
               | ElementNotInteractableException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            throw new AssertionFailedError();
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
