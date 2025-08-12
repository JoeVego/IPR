package api;

import io.qameta.allure.Allure;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

class RaRequests {

    /**
     * проверка гет запроса
     */
    static void getUsers(int id){
        given()
                .header("accept", "application/json")
        .when()
                .get("http://82.142.167.37:4879/users")
        .then()
                .assertThat()
                .statusCode(200)
                .body("[0].id", equalTo(id));
        Allure.step("вызов метода успешен");
    }

    static void getUserInfo(int id){
        given()
                .header("accept", "application/json")
        .when()
                .get("http://82.142.167.37:4879/user/" + id + "/info")
        .then()
                .assertThat()
                .statusCode(200)
                .body("cars[0].model.toString()",
                        equalTo("TestModel"));
        Allure.step("api method call successful");
    }
}
