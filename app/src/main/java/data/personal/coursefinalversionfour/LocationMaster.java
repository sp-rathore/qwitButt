package data.personal.coursefinalversionfour;

/**
 * Created by ilma on 15/03/2017.
 */

public class LocationMaster {
    private int id;
    private String name;
    public LocationMaster() {
    }

    public LocationMaster(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
