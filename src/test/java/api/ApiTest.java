package api;

import api.data.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ApiTest {

    @Test
    public void getUsersTest(){
        RaRequests.getUsers();
    }

    @Test
    public void getWithParamsTest(){
        RaRequests.getUserInfo(RaRequests.getId());
    }

    @Test
    public void getCarsTest(){
        Response response = ApacheRequests.getCars();
        Assert.assertNotNull(response);

        String respData = (String) response.getResponceBody();
        JsonElement jelement = new JsonParser().parseString(respData);
        System.out.println(jelement.getAsJsonArray().get(10).getAsJsonObject().get("mark"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals("Nissan", jelement.getAsJsonArray().get(10).getAsJsonObject().get("mark").getAsString());
    }

    @Test
    public void addCarTest(){
        Response response = ApacheRequests.addCar();

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
