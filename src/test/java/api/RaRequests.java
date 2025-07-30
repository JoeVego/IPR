package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

class RaRequests {

    /**
     * проверка гет запроса
     */
    static void getUsers(){
        given()
                .header("accept", "application/json")
        .when()
                .get("http://82.142.167.37:4879/users")
        .then()
                .assertThat()
                .statusCode(200)
                .body("[0].id.toString()", equalTo("6872"));
    }

    static void getUserInfo(){
        given()
                .header("accept", "application/json")
        .when()
                .get("http://82.142.167.37:4879/user/6872/info")
        .then()
                .assertThat()
                .statusCode(200)
                .body("cars[0].model.toString()",
                        equalTo("TestModel"));
    }
}
