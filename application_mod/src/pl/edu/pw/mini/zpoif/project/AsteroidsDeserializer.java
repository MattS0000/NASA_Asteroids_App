package pl.edu.pw.mini.zpoif.project;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import pl.edu.pw.mini.zpoif.project.Asteroid.AsteroidByDates;

import java.lang.reflect.Type;

public class AsteroidsDeserializer implements JsonDeserializer<AsteroidByDates> {
    public final static String TAG = "AsteroidsByDatesDeserializer";

    public AsteroidByDates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        Gson gson = new Gson();
        AsteroidByDates asteroidByDates = gson.fromJson(json, AsteroidByDates.class);
        asteroidByDates.setupAsteroidsMap();
        return asteroidByDates;
    }
}
