package exchange.rate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RateServiceImpl implements RateService{
    public double getRateApiCall() throws IOException {

        // rate value
        double value = 0.0;

        // set api URL
        URL apiURL = new URL("https://free.currconv.com/api/v7/convert?q=USD_EGP&compact=ultra&apiKey=60b30a67c9f5d8a4d4fc");

        // open connection
        HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();

        // set method
        connection.setRequestMethod("GET");

        // get response code
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // to read response
            String readLine;

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            }
            in .close();

            // parse from string to json object
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(response.toString());

            // get prop value and parse it to double
            value = Double.parseDouble(jsonObject.get("USD_EGP").toString());
        }

        // close connection
        connection.disconnect();
        return value;
    }

}
