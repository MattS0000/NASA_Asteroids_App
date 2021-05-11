package pl.edu.pw.mini.zpoif.project;

public class FastData {
    private String field;
    private String value;
    public FastData(String s1,Object o){
        this.field=s1;
        this.value=o == null||o=="null" ? "Error data processing" : o.toString();
    }

    public String getValue() {
        return value;
    }

    public String getField() {
        return field;
    }
}
