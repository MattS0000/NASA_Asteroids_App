package pl.edu.pw.mini.zpoif.project.Asteroid;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.stream.Collectors;

public class AsteroidByDates {

    @SerializedName("links")
    @Expose
    public Links links;
    @SerializedName("element_count")
    @Expose
    public Integer elementCount;
    @SerializedName("near_earth_objects")
    @Expose
    private Map<String, JsonArray> near_earth_objects;
    public Map<String, ArrayList<Asteroid>> mapOfAsteroids;

    public AsteroidByDates(Links links, Integer elementCount, Map<String, JsonArray> near_earth_objects){
        this.links = links;
        this.elementCount = elementCount;
        this.near_earth_objects = near_earth_objects;
    }

    public void join(AsteroidByDates asteroidByDates){
        for (Map.Entry<String, ArrayList<Asteroid>> entry : asteroidByDates.getEntrySet()){
            if (!mapOfAsteroids.containsKey(entry.getKey())) {
                mapOfAsteroids.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void setupAsteroidsMap(){
        this.mapOfAsteroids = JSONMapToAsteroids(near_earth_objects);
    }

    public Map<String, ArrayList<Asteroid>> getMapOfAsteroids() {
        return mapOfAsteroids;
    }

    public Set<String> getMapOfAsteroidsKeySet() {
        return mapOfAsteroids.keySet();
    }

    private Map<String,ArrayList<Asteroid>> JSONMapToAsteroids(Map<String, JsonArray> near_earth_objects) {
        Map<String, ArrayList<Asteroid>> asteroidMap = new HashMap<>();
        Gson gson = new Gson();
        for(Map.Entry<String,JsonArray> entry : near_earth_objects.entrySet()) {
            ArrayList<Asteroid> tmp = new ArrayList<>();
            for (JsonElement jsonElement : near_earth_objects.get(entry.getKey())){
                Asteroid asteroid = gson.fromJson(jsonElement,Asteroid.class);
                tmp.add(asteroid);
            }
            asteroidMap.put(entry.getKey(), tmp);
        }
        return asteroidMap;
    }

    private Set<Map.Entry<String, ArrayList<Asteroid>>> getEntrySet(){
        return mapOfAsteroids.entrySet();
    }
}
