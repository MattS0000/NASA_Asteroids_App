package pl.edu.pw.mini.zpoif.project.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.pw.mini.zpoif.project.Asteroid.Asteroid;
import pl.edu.pw.mini.zpoif.project.Asteroid.CloseApproachDatum;
import pl.edu.pw.mini.zpoif.project.FastData;

import java.util.ArrayList;
import java.util.List;

public class TablesWrapper {
    private ObservableList<FastData> dataA;
    private ObservableList<FastData> dataB;
    private ObservableList<FastData> dataC;

    public TablesWrapper(Asteroid j, Asteroid ShownAsteroid){
        ArrayList<FastData> Alist = new ArrayList<>();
        ArrayList<FastData> Blist = new ArrayList<>();
        ArrayList<FastData> Clist= new ArrayList<>();

        Alist.add(new FastData("Name:", ShownAsteroid.getName()));
        Alist.add(new FastData("Id", ShownAsteroid.getId()));
        List<String> orbits = ShownAsteroid.getOrbitedBodies();
        String bodies = orbits.get(0);
        for (int l = 1; l < orbits.size(); l++) {
            bodies += ", " + orbits.get(l);
        }
        Alist.add(new FastData("Orbited Bodies", bodies));
        Alist.add(new FastData("Velocity (km/s)", j.getFirstCloseApproachVelocity()));
        Alist.add(new FastData("Closest Approach", ShownAsteroid.getClosestApproach()));
        Alist.add(new FastData("Nasa Jpl Url", ShownAsteroid.getNasaJplUrl()));
        Alist.add(new FastData("Miss Distance (AU)", j.getFirstCloseApproachMissDistanceAU()));
        Alist.add(new FastData("Miss Distance (km)",j.getFirstCloseApproachMissDistanceKM()));
        Alist.add(new FastData("Absolute magnitude h", ShownAsteroid.getAbsoluteMagnitudeH()));
        Alist.add(new FastData("Average Diameter (m)", ShownAsteroid.getAverageDiameterMeters()));
        Alist.add(new FastData("Average Diameter (ft)", ShownAsteroid.getAverageDiameterFeet()));
        Alist.add(new FastData("Hazardous", ShownAsteroid.getPotentiallyHazardousAsteroid().toString()));
        Alist.add(new FastData("Sentry", ShownAsteroid.getSentryObject().toString()));

        Blist.add(new FastData("Orbit id", ShownAsteroid.getOrbitalData().getOrbitId()));
        Blist.add(new FastData("Orbit determination date", ShownAsteroid.getOrbitalData().getOrbitDeterminationDate()));
        Blist.add(new FastData("First observation date", ShownAsteroid.getOrbitalData().getFirstObservationDate()));
        Blist.add(new FastData("Last observation date", ShownAsteroid.getOrbitalData().getLastObservationDate()));
        Blist.add(new FastData("Data arc in days", ShownAsteroid.getOrbitalData().getDataArcInDays()));
        Blist.add(new FastData("Observations used", ShownAsteroid.getOrbitalData().getObservationsUsed().toString()));
        Blist.add(new FastData("Orbit uncertainty", ShownAsteroid.getOrbitalData().getOrbitUncertainty()));
        Blist.add(new FastData("Minimum orbit intersection", parser(ShownAsteroid.getOrbitalData().getMinimumOrbitIntersection())));
        Blist.add(new FastData("Jupiter tisserand invariant", ShownAsteroid.getOrbitalData().getJupiterTisserandInvariant()));
        Blist.add(new FastData("Epoch osculation", ShownAsteroid.getOrbitalData().getEpochOsculation()));
        Blist.add(new FastData("Eccentricity", parser(ShownAsteroid.getOrbitalData().getEccentricity())));
        Blist.add(new FastData("Semi major axis", parser(ShownAsteroid.getOrbitalData().getSemiMajorAxis())));
        Blist.add(new FastData("Ascending node longitude", ShownAsteroid.getOrbitalData().getAscendingNodeLongitude()));
        Blist.add(new FastData("Orbital period", ShownAsteroid.getOrbitalData().getOrbitalPeriod()));
        Blist.add(new FastData("Perihelion distance", parser(ShownAsteroid.getOrbitalData().getPerihelionDistance())));
        Blist.add(new FastData("Perihelion argument", ShownAsteroid.getOrbitalData().getPerihelionArgument()));
        Blist.add(new FastData("Aphelion distance", ShownAsteroid.getOrbitalData().getAphelionDistance()));
        Blist.add(new FastData("Perihelion time", ShownAsteroid.getOrbitalData().getPerihelionTime()));
        Blist.add(new FastData("Mean anomaly", ShownAsteroid.getOrbitalData().getMeanAnomaly()));
        Blist.add(new FastData("Mean motion", parser(ShownAsteroid.getOrbitalData().getMeanMotion())));
        Blist.add(new FastData("Equinox", ShownAsteroid.getOrbitalData().getEquinox()));
        List<CloseApproachDatum> list=ShownAsteroid.getCloseApproachData();
        for (CloseApproachDatum k : list) {
            Clist.add(new FastData(k.getCloseApproachDate(),k.getCloseApproachDate()));
        }
        dataA = FXCollections.observableArrayList(Alist);
        dataB = FXCollections.observableArrayList(Blist);
        dataC = FXCollections.observableArrayList(Clist);
    }

    public ObservableList<FastData> getDataA() {
        return dataA;
    }

    public ObservableList<FastData> getDataB() {
        return dataB;
    }

    public ObservableList<FastData> getDataC() {
        return dataC;
    }

    private String parser(String s) {
        return Double.toString(Double.parseDouble(s));
    }
}
