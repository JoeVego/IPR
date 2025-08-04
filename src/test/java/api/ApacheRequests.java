package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;

class ApacheRequests {
    static Car mazda_rx7;

    static {
        mazda_rx7 = new Car("Rotary",9811, "Mazda", "RX-7", new BigDecimal(7_000_000));
    }

    public static Response getCars(){
        Response response;

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {

            HttpGet httpGetCars = new HttpGet("http://82.142.167.37:4879/cars");

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpGetCars)) {

                response = new Response(closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
            }catch (IOException | ParseException exception) {
                exception.printStackTrace();
                return null;
            }

            return response;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Response addCar(){
        Response response;

        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            Gson carJson = new GsonBuilder().setPrettyPrinting().create();
            String carStr = carJson.toJson(mazda_rx7);

            HttpPost httpPost = new HttpPost("http://82.142.167.37:4879/car");
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
//            httpPost.setEntity(carStr);
            StringEntity carStrEnt = new StringEntity(carStr);
            httpPost.setEntity(carStrEnt);

            try (CloseableHttpResponse closeableResp = closeableHttpClient.execute(httpPost)) {

                response = new Response(closeableResp.getStatusLine().getStatusCode(),
                        EntityUtils.toString(closeableResp.getEntity()));
            }catch (IOException | ParseException exception) {
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
