package api;

import org.junit.jupiter.api.Test;

public class ApiTests {

    @Test
    public void getUsersTest(){
        RaRequests.getUsers();
    }

    @Test
    public void getWithParams(){
        RaRequests.getUserInfo();
    }
}
