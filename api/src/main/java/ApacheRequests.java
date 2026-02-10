import data.Car;
import data.Response;
import data.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

class ApacheRequests {

    @Step("запрос получения списка авто")
    public static Response getCars() {
        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            HttpGet httpGetCars = new HttpGet("http://82.142.167.37:4879/cars");

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpGetCars)) {
                Allure.step("Запрос выполнен", () -> Allure.addAttachment("Request", String.valueOf(httpGetCars)));

                Response response = new Response(closeableResp.getStatusLine().getStatusCode(), EntityUtils.toString(closeableResp.getEntity()));
                Allure.step("Ответ получен", () -> Allure.addAttachment("Response", String.valueOf(response)));

                return response;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Произошла непредвиденная ошибка: " + exception.getMessage());
        }
    }

    @Step("запрос добавления авто: {newCar}")
    public static Response addCar(Car newCar) {

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            Gson carJson = new GsonBuilder().setPrettyPrinting().create();
            String carStr = carJson.toJson(newCar);
            Allure.step("Создание объекта для сравнения", () -> Allure.addAttachment("Тестовый объект авто", String.valueOf(carStr)));

            HttpPost httpPost = new HttpPost("http://82.142.167.37:4879/car");
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity carStrEnt = new StringEntity(carStr);
            httpPost.setEntity(carStrEnt);

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpPost)) {
                Allure.step("Запрос выполнен", () -> Allure.addAttachment("Запрос", String.valueOf(httpPost)));

                Response response = new Response(closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
                Allure.step("Ответ получен", () -> Allure.addAttachment("Ответ", String.valueOf(response)));

                return response;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Произошла непредвиденная ошибка: " + exception.getMessage());
        }
    }

    @Step("Запрос добавления пользователя: {newUser}")
    public static Response putUser(User newUser) {
        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            Gson userJson = new GsonBuilder().setPrettyPrinting().create();
            String userStr = userJson.toJson(newUser);
            Allure.step("Создание объекта для сравнения", () -> Allure.addAttachment("Тестовый пользователь", String.valueOf(userStr)));

            HttpPut httpPut = new HttpPut("http://82.142.167.37:4879/user/1");
            httpPut.setHeader("accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            StringEntity userEnt = new StringEntity(userStr);
            httpPut.setEntity(userEnt);

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpPut)) {
                Allure.step("Запрос выполнен", () -> Allure.addAttachment("Запрос", String.valueOf(httpPut)));

                Response response = new Response(closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
                Allure.step("Ответ получен", () -> Allure.addAttachment("Ответ", String.valueOf(response)));

                return response;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Произошла непредвиденная ошибка: " + exception.getMessage());
        }
    }
}
