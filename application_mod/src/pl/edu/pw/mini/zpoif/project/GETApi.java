package pl.edu.pw.mini.zpoif.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.edu.pw.mini.zpoif.project.Asteroid.Asteroid;
import pl.edu.pw.mini.zpoif.project.Asteroid.AsteroidByDates;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;

public class GETApi {
    private static final String linkByDates = "https://api.nasa.gov/neo/rest/v1/feed?";
    private static final String linkByAsteroid = "https://api.nasa.gov/neo/rest/v1/neo/";
    private static final String KEY = "5EpY9UgP0FUU9sFbRfvbTX89ELGQSgjx84lLLmWC";
    private static final Gson gsonByDates = new GsonBuilder()
            .registerTypeAdapter(AsteroidByDates.class, new AsteroidsDeserializer())
            .create();
    private static final Gson gsonById = new Gson();

    //Functionality 1
    public static AsteroidByDates getAsteroidByDates(String start_date, String end_date) throws IOException {
        LocalDate startDateObj = LocalDate.parse(start_date);
        LocalDate endDateObj = LocalDate.parse(end_date);
        if (startDateObj.isAfter(endDateObj)){
            LocalDate temp = endDateObj;
            endDateObj = startDateObj;
            startDateObj = temp;
        }
        LocalDate finalEndDateObj = endDateObj;
        LocalDate weekLater = startDateObj.plusDays(7);
        if (weekLater.isAfter(endDateObj) || weekLater.isEqual(endDateObj)){
            return getOneWeekOfAsteroids(start_date, end_date);
        }

        AsteroidByDates asteroidByDates = getOneWeekOfAsteroids(startDateObj.toString(), weekLater.toString());
        weekLater.plusDays(1).datesUntil(endDateObj, Period.ofDays(8)).forEach(date -> {
            LocalDate weekAfter = date.plusDays(7);
            if (weekAfter.isAfter(finalEndDateObj)){
                weekAfter = finalEndDateObj;
            }
            AsteroidByDates temp;
            try {
                temp = getOneWeekOfAsteroids(date.toString(), weekAfter.toString());
                asteroidByDates.join(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return asteroidByDates;
    }

    public static Asteroid getAsteroidById(String id) throws IOException {
        HttpURLConnection asteroidConnection = getConnectionByAsteroid(id);
        String json = DataToString(asteroidConnection);
        Asteroid asteroid = gsonById.fromJson(json, Asteroid.class);
        return asteroid;
    }

    private static AsteroidByDates getOneWeekOfAsteroids(String start_date, String end_date) throws IOException {
        HttpURLConnection asteroidConnection = getConnectionByDates(start_date, end_date);
        String json = DataToString(asteroidConnection);
        AsteroidByDates asteroidByDates = gsonByDates.fromJson(json, AsteroidByDates.class);
        return asteroidByDates;
    }

    private static HttpURLConnection getConnectionByDates(String start_date, String end_date) {
        URL url = null;
        try {
            url = new URL(linkByDates + "start_date=" + start_date + "&end_date=" + end_date + "&api_key=" + KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            assert url != null;
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static HttpURLConnection getConnectionByAsteroid(String asteroid_id) {
        URL url = null;
        try {
            url = new URL(linkByAsteroid + asteroid_id + "?api_key=" + KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            assert url != null;
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static String DataToString(HttpURLConnection connection) throws IOException {
        InputStream inputStream = null ;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert inputStream != null;

        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String jsonText = readAll(rd);
        return jsonText;
    }
}
