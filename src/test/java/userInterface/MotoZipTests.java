package userInterface;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import userInterface.pages.BlogPage;
import userInterface.pages.OrderWindow;
import userInterface.pages.VacancyPage;
import userInterface.webdriver.Driver;
import userInterface.pages.HomePage;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import static props.ReadProperties.getProperty;

@Epic("Ю Ай тесты")
@Feature("Сайт перфа")
public class MotoZipTests {

    private WebDriver webDriver;
    private HomePage homePage;
    private BlogPage blogPage;
    private OrderWindow orderWindow;
    private VacancyPage vacancyPage;

    @BeforeEach
    void init(){
        webDriver = new Driver().getDriver();
        homePage = new HomePage(webDriver);
        blogPage = new BlogPage(webDriver);
        orderWindow = new OrderWindow(webDriver);
        vacancyPage = new VacancyPage(webDriver);

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
        catch (AssertionFailedError | NullPointerException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            //чтобы тест успешным не отметилс в аллюре
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
            orderWindow.isEmailFiledTipDisplayed();
            orderWindow.isNameFiledTipDisplayed();
            orderWindow.isOrgFiledTipDisplayed();
            orderWindow.isPhoneFiledTipDisplayed();
            Allure.step("все подсказки присутствуют");

            orderWindow.sendButtonClick();
            orderWindow.isMainTipDisplayed();
            Allure.step("Основная проверка присутствует");
        }
        catch (AssertionFailedError | NullPointerException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            //чтобы тест успешным не отметился в аллюре
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
        catch (AssertionFailedError | NullPointerException exc){
            takeScreenshot();
            webDriver.close();

            exc.printStackTrace();
            Allure.step("тест неуспешен, драйвер закрыт");
            //чтобы тест успешным не отметилс в аллюре
            throw new AssertionFailedError();
        }
    }

    private void takeScreenshot() {
        byte[] src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("screen", new ByteArrayInputStream(src));
    }
}
