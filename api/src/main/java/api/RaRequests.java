package api;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.opentest4j.AssertionFailedError;

class RaRequests {

    /**
     * проверка гет запроса
     */
    @Step
    static void getUsers(int id){
        try {
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
        catch (AssertionError exc) {
            throw new AssertionFailedError("Тест упал: " + exc.getMessage());
        } catch (Exception exc) {
            // Обработка других исключений
            Allure.step("Произошла непредвиденная ошибка: " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }

    @Step
    static void getUserInfo(int id){
        try {
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
        catch (AssertionError exc) {
            throw new AssertionFailedError("Тест упал: " + exc.getMessage());
        } catch (Exception exc) {
            // Обработка других исключений
            Allure.step("Произошла непредвиденная ошибка: " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }
}
