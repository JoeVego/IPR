package api;

import api.data.Car;
import api.data.Response;
import api.data.User;
import api.data.UserSex;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

public class ApiTest {

    Car mazda_rx7;
    User someUser;
    Response response;

    @BeforeEach
    void init() {
        mazda_rx7 = new Car("Diesel",9811, "Mazda", "RX-7", new BigDecimal(7_000_000));
        someUser = new User(21, "Joe", 991, new BigDecimal("2334.4"), "Vego", UserSex.MALE);
    }

    @Tag("Users")
    @Test
    public void testGetUsers(){
        RaRequests.getUsers();
    }

    @Tag("Users")
    @Test
    public void testGetWithParams(){
        RaRequests.getUserInfo(RaRequests.getId());
    }

    @Tag("Cars")
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
    @Test
    public void testAddCar(){
        response = ApacheRequests.addCar(mazda_rx7);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), 200);
    }

    @Tag("Users")
    @Test
    public void testPutUser(){
        response = ApacheRequests.putUser(someUser);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), 200);
    }
}
