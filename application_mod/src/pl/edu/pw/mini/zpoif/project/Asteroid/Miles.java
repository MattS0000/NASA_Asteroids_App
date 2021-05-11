package pl.edu.pw.mini.zpoif.project.Asteroid;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Miles {
    @SerializedName("estimated_diameter_min")
    @Expose
    private Double estimatedDiameterMin;
    @SerializedName("estimated_diameter_max")
    @Expose
    private Double estimatedDiameterMax;
}