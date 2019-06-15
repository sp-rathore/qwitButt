package data.personal.coursefinalversionfour;

/**
 * Created by ShishupalRathore on 19/04/17.
 */

public class CigCountLocation {

    private int cig_count_loc;
    private String location_name;

    public CigCountLocation() {    }

    public CigCountLocation(int cig_count_loc, String location_name)
    {
        System.out.println("RM1 - Cig Count: " + location_name.toString());
        this.cig_count_loc      = cig_count_loc;
        this.location_name      = location_name;
    }


    public void setCigCountLoc(int cig_count_loc)
    {this.cig_count_loc = cig_count_loc;}

    public void setLocationName(String location_name)
    {this.location_name = location_name;}

    public int getCigCountLoc()
    { return cig_count_loc; }

    public String getLocationName()
    { return location_name; }

}
