package pl.edu.pw.mini.zpoif.project.Asteroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Asteroid {
    @SerializedName("links")
    @Expose
    private final Links links;
    @SerializedName("id")
    @Expose
    private final String id;
    @SerializedName("neo_reference_id")
    @Expose
    private final String neoReferenceId;
    @SerializedName("name")
    @Expose
    private final String name;
    @SerializedName("designation")
    @Expose
    private final String designation;
    @SerializedName("nasa_jpl_url")
    @Expose
    private final String nasaJplUrl;
    @SerializedName("absolute_magnitude_h")
    @Expose
    private final Double absoluteMagnitudeH;
    @SerializedName("estimated_diameter")
    @Expose
    private final EstimatedDiameter estimatedDiameter;
    @SerializedName("is_potentially_hazardous_asteroid")
    @Expose
    private final Boolean isPotentiallyHazardousAsteroid;
    @SerializedName("close_approach_data")
    @Expose
    private final List<CloseApproachDatum> closeApproachData;
    @SerializedName("orbital_data")
    @Expose
    private final OrbitalData orbitalData;
    @SerializedName("is_sentry_object")
    @Expose
    private final Boolean isSentryObject;

    public Asteroid(Links links, String id, String neoReferenceId, String name, String designation, String nasaJplUrl, Double absoluteMagnitudeH, EstimatedDiameter estimatedDiameter, Boolean isPotentiallyHazardousAsteroid, List<CloseApproachDatum> closeApproachData, OrbitalData orbitalData, Boolean isSentryObject) {
        this.links = links;
        this.id = id;
        this.neoReferenceId = neoReferenceId;
        this.name = name;
        this.designation = designation;
        this.nasaJplUrl = nasaJplUrl;
        this.absoluteMagnitudeH = absoluteMagnitudeH;
        this.estimatedDiameter = estimatedDiameter;
        this.isPotentiallyHazardousAsteroid = isPotentiallyHazardousAsteroid;
        this.closeApproachData = closeApproachData;
        this.orbitalData = orbitalData;
        this.isSentryObject = isSentryObject;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNasaJplUrl() {
        return nasaJplUrl;
    }
    public Double getAbsoluteMagnitudeH() {
        return absoluteMagnitudeH;
    }
    public EstimatedDiameter getEstimatedDiameter() {
        return estimatedDiameter;
    }
    public Boolean getPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }
    public List<CloseApproachDatum> getCloseApproachData() {
        return closeApproachData;
    }
    public OrbitalData getOrbitalData() {
        return orbitalData;
    }
    public Boolean getSentryObject() {
        return isSentryObject;
    }

    //Functionality 10
    public List<String> getOrbitedBodies(){
        return closeApproachData.stream()
                .map(CloseApproachDatum::getOrbitingBody).distinct().collect(Collectors.toList());
    }

    public double getFirstCloseApproachVelocity(){
        return closeApproachData.get(0).getVelocity();
    }

    public double getFirstCloseApproachMissDistanceAU(){
        return closeApproachData.get(0).getMissedDistanceInAU();
    }
    public double getFirstCloseApproachMissDistanceKM(){
        return closeApproachData.get(0).getMissedDistanceInKM();
    }

    public double getAverageDiameterMeters(){
        return estimatedDiameter.getAvgDiameterInMeters();
    }
    public double getAverageDiameterFeet(){
        return estimatedDiameter.getAvgDiameterInFeet();
    }

    //Functionality 6
    public String getClosestApproach(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        Optional<CloseApproachDatum> date = closeApproachData.stream().filter(datum -> {
            LocalDate approach = LocalDate.parse(datum.getCloseApproachDate(), formatter);
            return approach.isAfter(today);
        }).findFirst();
        if (date.isPresent()){
            return date.get().getCloseApproachDate();
        } else {
            return "No future approach found";
        }
    }
}
