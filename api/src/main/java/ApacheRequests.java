import data.Car;
import data.Response;
import data.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class ApacheRequests {

    @Step("запрос получения списка авто")
    public static Response getCars(){
        Response response;

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {

            HttpGet httpGetCars = new HttpGet("http://82.142.167.37:4879/cars");

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpGetCars)) {
                Allure.addAttachment("Request", String.valueOf(httpGetCars));
                Allure.step("Гет запрос выполнен");

                response = new Response(closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
                Allure.addAttachment("Response", String.valueOf(response));
                Allure.step("ответ сохранен");

            }catch (IOException | ParseException exception) {
                Allure.step("ошибка выполнения запроса");
                exception.printStackTrace();
                return null;
            }

            return response;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Step("запрос добавления авто")
    public static Response addCar(Car newCar){

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            Gson carJson = new GsonBuilder().setPrettyPrinting().create();
            String carStr = carJson.toJson(newCar);
            Allure.step("Создан объект для сравнения");

            HttpPost httpPost = new HttpPost("http://82.142.167.37:4879/car");
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity carStrEnt = new StringEntity(carStr);
            httpPost.setEntity(carStrEnt);

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpPost)) {
                Allure.addAttachment("Request", String.valueOf(httpPost));
                Allure.step("Пост запрос выполнен");

                Response response = new Response(closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
                Allure.addAttachment("Response", String.valueOf(response));
                Allure.step("ответ сохранен");

                return response;

            }catch (IOException | ParseException exception) {
                exception.printStackTrace();
                return null;
            }
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Step("запрос добавления пользователя")
    public static Response putUser(User newUser){
        Response response;

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            Gson userJson = new GsonBuilder().setPrettyPrinting().create();
            String userStr = userJson.toJson(newUser);

            HttpPut httpPut = new HttpPut("http://82.142.167.37:4879/user/1");
            httpPut.setHeader("accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");

            StringEntity userEnt = new StringEntity(userStr);
            httpPut.setEntity(userEnt);

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpPut)) {
                Allure.addAttachment("Request", String.valueOf(httpPut));
                Allure.step("Пут запрос выполнен");

                response = new Response(
                        closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
                Allure.addAttachment("Response", String.valueOf(response));
                Allure.step("ответ сохранен");

            }catch (IOException | ParseException exception) {
                Allure.step("ошибка выполнения запроса");
                exception.printStackTrace();
                return null;
            }

            return response;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
