import data.Car;
import data.Response;
import data.User;
import data.UserSex;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;

import java.math.BigDecimal;

@Epic("Апи тесты")
@Feature("Свой полигон апи 1 ссылка сваггер")
public class ApiTest {

    private Car mazda_rx7;
    private User someUser;
    private Response response;
    private int id;
    private int userId;

    @BeforeEach
    void init() {
        mazda_rx7 = new Car("Diesel",98111,
                "Mazda", "RX-7", new BigDecimal(7_000_000));
        someUser = new User(21, "Joe",
                991, new BigDecimal("2334.4"), "Vego", UserSex.MALE);
        id = 6890;
        userId = 7179;
        Allure.step("init values success");

        RestAssured.filters(new AllureRestAssured());
    }

    @Tag("Users")
    @Tag("RestAssured")
    @Description("Проверка получения списка пользователей")
    @Owner("Lupa")
    @DisplayName("Проверка получения списка пользователей")
    @Test
    public void testGetUsers(){
        Allure.step("Выполнение запроса получения пользвовате" +
                "лей", () -> {
            Allure.parameter("userID", userId);
            RaRequests.getUsers(userId);
        });
    }

    @Tag("Users")
    @Tag("RestAssured")
    @Description("Проверка получения списка пользователей " +
            "запрос гет с параметром")
    @DisplayName("Проверка получения списка пользователей " +
            "запрос гет с параметром")
    @Owner("Pupa")
    @Test
    public void testGetWithParams(){
        Allure.parameter("ID", id);

        Allure.step("test get user info", () -> RaRequests.getUserInfo(id));
    }

    @Tag("Cars")
    @Tag("Apache_http")
    @Description("Проверка получения списка авто парсинг джейсона")
    @DisplayName("Проверка получения списка авто парсинг джейсона")
    @Test
    public void testGetCars(){
        try {
            response = ApacheRequests.getCars();
            Assertions.assertNotNull(response);
            Allure.step("Ответ получен");

            String respData = (String) response.getResponseBody();
            JsonElement jsonElement = JsonParser.parseString(respData);

            Allure.step("Шаг проверок", () -> {
                Assertions.assertEquals(response.getStatusCode(), 200);
                Allure.step("код ответа корректен");

                Assertions.assertEquals("Volvo",
                        jsonElement.getAsJsonArray()
                                .get(10)
                                .getAsJsonObject()
                                .get("mark")
                                .getAsString());
                Allure.step("значение марки корректно");
            });
        }
        catch (AssertionError exc) {
            throw new AssertionFailedError("Тест упал: " + exc.getMessage());

        } catch (Exception exc) {
            Allure.step("Произошла непредвиденная ошибка: " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }

    @Tag("Cars")
    @Tag("Apache_http")
    @Description("Проверка добавления авто запрос с телом джейсоном")
    @DisplayName("Проверка добавления авто запрос с телом джейсоном")
    @Test
    public void testAddCar(){
        try{
            Allure.parameter("car", mazda_rx7);
            response = ApacheRequests.addCar(mazda_rx7);
            Allure.step("Запрос выполнен");

            Assertions.assertNotNull(response);
            Assertions.assertEquals(200, response.getStatusCode());
            Allure.step("Получен успешный код ответа");
        }
        catch (AssertionError exc) {
            throw new AssertionFailedError("Тест упал: " + exc.getMessage());
        } catch (Exception exc) {
            // Обработка других исключений
            Allure.step("Произошла непредвиденная ошибка: " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }

    @Tag("Users")
    @Tag("Apache_http")
    @Description("Проверка добавления пользователя")
    @DisplayName("Проверка добавления пользователя")
    @Test
    public void testPutUser(){
        try {
            Allure.parameter("user", someUser);
            response = ApacheRequests.putUser(someUser);
            Allure.step("Запрос выполнен");

            Assertions.assertNotNull(response);
            Assertions.assertEquals(200, response.getStatusCode());
            Allure.step("Получен успешный код ответа");
        }
        catch (AssertionError exc) {
            throw new AssertionFailedError("Тест упал: " + exc.getMessage());

        } catch (Exception exc) {
            // Обработка других исключений
            Allure.step("Произошла непредвиденная ошибка: " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }
}
