import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;

class RaRequests {

    /**
     * проверка гет запроса
     */
    @Step("Вызовы методе получения пользователя по айди: {id}")
    static void getUsers(int id) {
        RestAssured.given()
                .header("accept", "application/json")
                .when()
                .get("http://82.142.167.37:4879/users")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].id", CoreMatchers.equalTo(id));
    }

    @Step("Получение информации пользователя по айди: {id}")
    static void getUserInfo(int id) {
        RestAssured.given()
                .header("accept", "application/json")
                .when()
                .get("http://82.142.167.37:4879/user/" + id + "/info")
                .then()
                .assertThat()
                .statusCode(200)
                .body("cars[0].model.toString()",
                        CoreMatchers.equalTo("TestModel"));
    }
}
