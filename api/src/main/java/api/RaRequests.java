package api;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;

class RaRequests {

    /**
     * проверка гет запроса
     */
    static void getUsers(int id){
        RestAssured.given()
                .header("accept", "application/json")
        .when()
                .get("http://82.142.167.37:4879/users")
        .then()
                .assertThat()
                .statusCode(200)
                .body("[0].id", CoreMatchers.equalTo(id));
        Allure.step("вызов метода успешен");
    }

    static void getUserInfo(int id){
        RestAssured.given()
                .header("accept", "application/json")
        .when()
                .get("http://82.142.167.37:4879/user/" + id + "/info")
        .then()
                .assertThat()
                .statusCode(200)
                .body("cars[0].model.toString()",
                        CoreMatchers.equalTo("TestModel"));
        Allure.step("api method call successful");
    }
}
