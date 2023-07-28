import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {
    private static final String API_KEY = "b6907d289e10d714a6e88b30761fae22";
    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";

    private static String getWeatherData(String date) {
        try{
        String data = "";
          // Create a URL object from the API URL string
            URL url = new URL(BASE_URL);

            StringBuilder response = new StringBuilder();
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request method to GET
            connection.setRequestMethod("GET");
            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // If the response is successful (HTTP 200 OK)

                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
               
                String line;

                // Read the response line by line and append to the 'response' StringBuilder
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Close the BufferedReader
                reader.close();

                // Print the response
                System.out.println("Response from API:");
                System.out.println(response.toString());
            } else {
                // If the response is not successful, print the error message
                System.out.println("Error: Unable to fetch data. Response Code: " + responseCode);
            }
            // Close the connection
            connection.disconnect();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static double getTemperature(String data) {
        // Parse the JSON data and extract the temperature value.
        // You can use libraries like Jackson or Gson for JSON parsing.
        // For simplicity, we'll just return a sample temperature value here.
        
       // Gson gson = new Gson();
        //JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        //if (jsonObject.has("list")) {
          //  JsonArray hobbiesArray = jsonObject.getAsJsonArray("list");
            //String[] hobbies = gson.fromJson(hobbiesArray, String[].class);
            //System.out.println("Temperature: " + hobbies);
        //} else {
          //  System.out.println("Temperature not found in the JSON data.");
        //}
        return 23.5;
    }

    private static double getWindSpeed(String data) {
        // Parse the JSON data and extract the wind speed value.
        // You can use libraries like Jackson or Gson for JSON parsing.
        // For simplicity, we'll just return a sample wind speed value here.
        return 12.3;
    }

    private static double getPressure(String data) {
        // Parse the JSON data and extract the pressure value.
        // You can use libraries like Jackson or Gson for JSON parsing.
        // For simplicity, we'll just return a sample pressure value here.
        return 1012.2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Get weather");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    String weatherData = getWeatherData(date);
                    double temperature = getTemperature(weatherData);
                    System.out.println("Temperature on " + date + ": " + temperature + "°C\n");
                    break;
                case 2:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    weatherData = getWeatherData(date);
                    double windSpeed = getWindSpeed(weatherData);
                    System.out.println("Wind Speed on " + date + ": " + windSpeed + " kph\n");
                    break;
                case 3:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    weatherData = getWeatherData(date);
                    double pressure = getPressure(weatherData);
                    System.out.println("Pressure on " + date + ": " + pressure + " mb\n");
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}
