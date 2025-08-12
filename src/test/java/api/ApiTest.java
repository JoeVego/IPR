package api;

import api.data.Car;
import api.data.Response;
import api.data.User;
import api.data.UserSex;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;

public class ApiTest {

    private Car mazda_rx7;
    private User someUser;
    private Response response;
    private int id;
    private int userId;

    @BeforeEach
    void init() {
        mazda_rx7 = new Car("Diesel",9811,
                "Mazda", "RX-7", new BigDecimal(7_000_000));
        someUser = new User(21, "Joe",
                991, new BigDecimal("2334.4"), "Vego", UserSex.MALE);
        id = 6890;
        userId = 2759;
        Allure.step("init values success");
    }

    @Tag("Users")
    @Tag("RestAssured")
    @Description("Проверка получения списка пользователей")
    @Owner("Lupa")
    @Test
    public void testGetUsers(){
        Allure.step("starting test get user");
        Allure.parameter("userID", userId);
        RaRequests.getUsers(userId);
        Allure.step("test run successful");
    }

    @Tag("Users")
    @Tag("RestAssured")
    @Description("Проверка получения списка пользователей " +
            "запрос гет с параметром")
    @Owner("Pupa")
    @Test
    public void testGetWithParams(){
        Allure.parameter("ID", id);
        RaRequests.getUserInfo(id);
    }

    @Tag("Cars")
    @Tag("Apache http")
    @Description("Проверка получения списка авто" +
            "парсинг джейсона")
    @Test
    public void testGetCars(){
        response = ApacheRequests.getCars();
        Assertions.assertNotNull(response);

        String respData = (String) response.getResponseBody();
        JsonElement jsonElement = JsonParser.parseString(respData);
        System.out.println(jsonElement.getAsJsonArray().get(10).getAsJsonObject().get("mark"));

        Assertions.assertEquals(response.getStatusCode(), 200);
        Assertions.assertEquals("Nissan", jsonElement.getAsJsonArray().get(10).getAsJsonObject().get("mark").getAsString());
    }

    @Tag("Cars")
    @Tag("Apache http")
    @Description("Проверка добавления авто" +
            "запрос с телом джейсоном")
    @Test
    public void testAddCar(){
        Allure.parameter("car", mazda_rx7);
        response = ApacheRequests.addCar(mazda_rx7);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), 200);
    }

    @Tag("Users")
    @Tag("Apache http")
    @Description("Проверка получения списка авто" +
            "запрос пут")
    @Test
    public void testPutUser(){
        Allure.parameter("user", someUser);
        response = ApacheRequests.putUser(someUser);

        Assertions.assertNotNull(response);
        try{
            Assertions.assertEquals(response.getStatusCode(), 300);
        }
        catch (AssertionFailedError exc){
            toFile(response.toString());
            Allure.addAttachment("Answer", "allure-results/filename.txt");

            try {
                Allure.addAttachment("File", new FileInputStream("allure-results/filename.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String toFile(String text){
        try (PrintWriter out = new PrintWriter("allure-results/filename.txt")) {
            out.println(text);
        }
        catch (FileNotFoundException fnExc){
            fnExc.printStackTrace();
            return null;
        }

        return null;
    }
}
