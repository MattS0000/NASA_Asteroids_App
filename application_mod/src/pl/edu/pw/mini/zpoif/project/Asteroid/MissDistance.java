package pl.edu.pw.mini.zpoif.project.Asteroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MissDistance {
    @SerializedName("astronomical")
    @Expose
    private String astronomical;
    @SerializedName("lunar")
    @Expose
    private String lunar;
    @SerializedName("kilometers")
    @Expose
    private String kilometers;
    @SerializedName("miles")
    @Expose
    private String miles;

    public String getAstronomical() {
        return astronomical;
    }
    public String getKilometers() {
        return kilometers;
    }
}
