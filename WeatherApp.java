import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.net.URLEncoder;
import java.util.Properties;
import java.io.FileInputStream;

import org.json.JSONObject;
import org.json.JSONArray;

public class WeatherApp {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String cityName;
        String cityState;
        String country;

        while (true) {
            // Ask user for city name and encode it for the URL
            System.out.println("Enter City Name");
            String cityInput = URLEncoder.encode(scanner.nextLine(), "UTF-8");

            // Allow user to exit
            if (cityInput.equalsIgnoreCase("exit")) break;

            // Ask for state name
            System.out.println("Enter State Name");
            String stateInput = scanner.nextLine().trim();
            if (stateInput.equalsIgnoreCase("exit")) break;

            // Load API key from config.properties file
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            String apiKey = prop.getProperty("API_KEY");

            // Handle missing API key
            if (apiKey == null || apiKey.isEmpty()) {
                System.out.println("API Key is empty, please check config.properties");
                return;
            }

            // Convert full state name to abbreviation
            cityName = cityInput;
            cityState = States.convertToAbbreviation(stateInput);
            country = "US";

            // Combine city, state, and country into query format
            String query = cityName + "," + cityState + "," + country;

            // Build API URL
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + query + "&appid=" + apiKey + "&units=metric";

            // Check if state was recognized before making the request
            if (cityState.equals("Unknown")) {
                System.out.println("State not recognized. Please try again.");
                continue;
            } else {
                // Call helper method to make the API request and print weather info
                setCity(cityName, cityState, country, urlString);
            }
        }
    }

    // Makes the HTTP request and prints the weather details
    static void setCity(String cityName, String cityState, String country, String urlString) throws Exception {
        try {
            // Open HTTP connection and send GET request
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject json = new JSONObject(response.toString());

            // Extract weather description
            JSONArray weatherArray = json.getJSONArray("weather");
            String description = weatherArray.getJSONObject(0).getString("description");

            // Extract temperature and humidity
            JSONObject main = json.getJSONObject("main");
            double temp = main.getDouble("temp");
            double fahrenheit = (temp * 9 / 5) + 32;
            int humidity = main.getInt("humidity");

            // Print the weather information
            System.out.println("City: " + cityName.replace("+", " "));
            System.out.println("State: " + cityState.replace("+", " "));
            System.out.println("Country: " + country);
            System.out.println("Temperature: " + temp + "°C / " + fahrenheit + "°F");
            System.out.println("Description: " + description);
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception e) {
            // Print stack trace if something goes wrong
            e.printStackTrace();
        }
    }
}
