package pl.edu.pw.mini.zpoif.project.Asteroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrbitClass {
    @SerializedName("orbit_class_type")
    @Expose
    private String orbitClassType;
    @SerializedName("orbit_class_description")
    @Expose
    private String orbitClassDescription;
    @SerializedName("orbit_class_range")
    @Expose
    private String orbitClassRange;
}
