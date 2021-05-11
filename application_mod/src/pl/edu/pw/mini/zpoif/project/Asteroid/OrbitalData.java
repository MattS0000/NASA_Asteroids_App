package pl.edu.pw.mini.zpoif.project.Asteroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrbitalData {
    @SerializedName("orbit_id")
    @Expose
    private String orbitId;
    @SerializedName("orbit_determination_date")
    @Expose
    private String orbitDeterminationDate;
    @SerializedName("first_observation_date")
    @Expose
    private String firstObservationDate;
    @SerializedName("last_observation_date")
    @Expose
    private String lastObservationDate;
    @SerializedName("data_arc_in_days")
    @Expose
    private Integer dataArcInDays;
    @SerializedName("observations_used")
    @Expose
    private Integer observationsUsed;
    @SerializedName("orbit_uncertainty")
    @Expose
    private String orbitUncertainty;
    @SerializedName("minimum_orbit_intersection")
    @Expose
    private String minimumOrbitIntersection;
    @SerializedName("jupiter_tisserand_invariant")
    @Expose
    private String jupiterTisserandInvariant;
    @SerializedName("epoch_osculation")
    @Expose
    private String epochOsculation;
    @SerializedName("eccentricity")
    @Expose
    private String eccentricity;
    @SerializedName("semi_major_axis")
    @Expose
    private String semiMajorAxis;
    @SerializedName("inclination")
    @Expose
    private String inclination;
    @SerializedName("ascending_node_longitude")
    @Expose
    private String ascendingNodeLongitude;
    @SerializedName("orbital_period")
    @Expose
    private String orbitalPeriod;
    @SerializedName("perihelion_distance")
    @Expose
    private String perihelionDistance;
    @SerializedName("perihelion_argument")
    @Expose
    private String perihelionArgument;
    @SerializedName("aphelion_distance")
    @Expose
    private String aphelionDistance;
    @SerializedName("perihelion_time")
    @Expose
    private String perihelionTime;
    @SerializedName("mean_anomaly")
    @Expose
    private String meanAnomaly;
    @SerializedName("mean_motion")
    @Expose
    private String meanMotion;
    @SerializedName("equinox")
    @Expose
    private String equinox;
    @SerializedName("orbit_class")
    @Expose
    private OrbitClass orbitClass;

    public String getOrbitId() {
        return orbitId;
    }

    public String getOrbitDeterminationDate() {
        return orbitDeterminationDate;
    }

    public String getFirstObservationDate() {
        return firstObservationDate;
    }

    public String getLastObservationDate() {
        return lastObservationDate;
    }

    public Integer getDataArcInDays() {
        return dataArcInDays;
    }

    public Integer getObservationsUsed() {
        return observationsUsed;
    }

    public String getOrbitUncertainty() {
        return orbitUncertainty;
    }

    public String getMinimumOrbitIntersection() {
        return minimumOrbitIntersection;
    }

    public String getJupiterTisserandInvariant() {
        return jupiterTisserandInvariant;
    }

    public String getEpochOsculation() {
        return epochOsculation;
    }

    public String getEccentricity() {
        return eccentricity;
    }

    public String getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public String getAscendingNodeLongitude() {
        return ascendingNodeLongitude;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getPerihelionDistance() {
        return perihelionDistance;
    }

    public String getPerihelionArgument() {
        return perihelionArgument;
    }

    public String getAphelionDistance() {
        return aphelionDistance;
    }

    public String getPerihelionTime() {
        return perihelionTime;
    }

    public String getMeanAnomaly() {
        return meanAnomaly;
    }

    public String getMeanMotion() {
        return meanMotion;
    }

    public String getEquinox() {
        return equinox;
    }
}
