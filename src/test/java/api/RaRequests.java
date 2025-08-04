package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

class RaRequests {
    private static int id = 6890;

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
                .body("[0].id", equalTo(id));
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
    }

    public static int getId(){
        return id;
    }
}
