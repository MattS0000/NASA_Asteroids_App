package pl.edu.pw.mini.zpoif.project.Asteroid;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimatedDiameter {
    @SerializedName("kilometers")
    @Expose
    private Kilometers kilometers;
    @SerializedName("meters")
    @Expose
    private Meters meters;
    @SerializedName("miles")
    @Expose
    private Miles miles;
    @SerializedName("feet")
    @Expose
    private Feet feet;

    public double getAvgDiameterInMeters(){
        return meters.getEstimatedDiameterAvg();
    }
    public double getAvgDiameterInFeet(){
        return feet.getEstimatedDiameterAvg();
    }
}
