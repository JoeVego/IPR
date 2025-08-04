package api;

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

        assert response != null;
    }

    @Test
    public void addCarTest(){
        Response response = ApacheRequests.addCar();
        System.out.println(response);
        assert response != null;
    }
}
