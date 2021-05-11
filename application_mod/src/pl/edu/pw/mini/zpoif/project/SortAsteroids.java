package pl.edu.pw.mini.zpoif.project;

import pl.edu.pw.mini.zpoif.project.Asteroid.Asteroid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortAsteroids {
    //Functionality 4
    public static void sortByVelocityAscending(List<Asteroid> asteroidList){
        asteroidList.sort(Comparator.comparing(Asteroid::getFirstCloseApproachVelocity));
    }

    public static void sortByVelocityDescending(List<Asteroid> asteroidList){
        asteroidList.sort(Comparator.comparing(Asteroid::getFirstCloseApproachVelocity).reversed());
    }

    public static void sortByMissDistanceAscending(List<Asteroid> asteroidList){
        asteroidList.sort(Comparator.comparing(Asteroid::getFirstCloseApproachMissDistanceAU));
    }

    public static void sortByMissDistanceDescending(List<Asteroid> asteroidList){
        asteroidList.sort(Comparator.comparing(Asteroid::getFirstCloseApproachMissDistanceAU).reversed());
    }

    public static void sortByDiameterAscending(List<Asteroid> asteroidList){
        asteroidList.sort(Comparator.comparing(Asteroid::getAverageDiameterMeters));
    }

    public static void sortByDiameterDescending(List<Asteroid> asteroidList){
        asteroidList.sort(Comparator.comparing(Asteroid::getAverageDiameterMeters).reversed());
    }
    public static void filterBySentry(ArrayList<Asteroid> entry){
        entry.removeIf(asteroid -> !asteroid.getSentryObject());
    }

    public static void filterByHazardous(ArrayList<Asteroid> entry){
        entry.removeIf(asteroid -> !asteroid.getPotentiallyHazardousAsteroid());
    }

    public static void filterByBothDangers(ArrayList<Asteroid> entry){
        entry.removeIf(asteroid -> !(asteroid.getSentryObject() && asteroid.getPotentiallyHazardousAsteroid()));
    }
}
