package api;

import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class ApacheRequests {

    public Response getCars(){
        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet("http://82.142.167.37:4879/cars");

            closeableHttpClient.execute(httpGet);

            int responseCode = closeableHttpClient.execute(httpGet,
                    httpResponse -> httpResponse.getStatusLine().getStatusCode());

            String responseData = closeableHttpClient.execute(httpGet,
                    httpResponse -> EntityUtils.toString(httpResponse.getEntity()));

            return null;
//            return new Response(responseCode, responseData);
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
