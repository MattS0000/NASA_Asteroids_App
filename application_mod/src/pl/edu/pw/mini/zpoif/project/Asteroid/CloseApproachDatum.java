package pl.edu.pw.mini.zpoif.project.Asteroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CloseApproachDatum {
    @SerializedName("close_approach_date")
    @Expose
    private String closeApproachDate;
    @SerializedName("close_approach_date_full")
    @Expose
    private String closeApproachDateFull;
    @SerializedName("epoch_date_close_approach")
    @Expose
    private BigInteger epochDateCloseApproach;
    @SerializedName("relative_velocity")
    @Expose
    private RelativeVelocity relativeVelocity;
    @SerializedName("miss_distance")
    @Expose
    private MissDistance missDistance;
    @SerializedName("orbiting_body")
    @Expose
    private String orbitingBody;

    public String getCloseApproachDate() {
        return closeApproachDate;
    }
    public RelativeVelocity getRelativeVelocity() {
        return relativeVelocity;
    }
    public MissDistance getMissDistance() {
        return missDistance;
    }
    public String getOrbitingBody() {
        return orbitingBody;
    }

    public double getVelocity(){
        return Double.parseDouble(getRelativeVelocity().getKilometersPerSecond());
    }

    public double getMissedDistanceInAU(){
        return Double.parseDouble(getMissDistance().getAstronomical());
    }

    public double getMissedDistanceInKM(){
        return Double.parseDouble(getMissDistance().getKilometers());
    }
}