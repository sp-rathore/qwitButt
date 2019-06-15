package data.personal.coursefinalversionfour;
import java.lang.*;
import java.util.Calendar;

/**
 * Created by ShishupalRathore on 19/03/17.
 */

public class EventMaster {
    private int event_id;
    private int event_period_id;
    private int event_user_id;
    private int event_location_id;
    private double event_latitude;
    private double event_longitude;
    private String event_date_time;
    //private String  event_time;
    public  EventMaster() {    }
    public  EventMaster(int event_period_id, int event_user_id, int event_location_id,
                        double event_latitude, double event_longitude, String event_date_time)
    {
        this.event_period_id        = event_period_id;
        this.event_user_id          = event_user_id;
        this.event_location_id      = event_location_id;
        this.event_latitude         = event_latitude;
        this.event_longitude        = event_longitude;
        this.event_date_time             = event_date_time;
    }

    public EventMaster(double event_latitude,double event_longitude, String event_date_time)
    {    this.event_latitude         = event_latitude;
        this.event_longitude        = event_longitude;
        this.event_date_time             = event_date_time;
    }

    public void setEventId(int event_id)
    { this.event_id = event_id;}

    public void setPeriodID(int event_period_id)
    { this.event_period_id = event_period_id;}

    public void setEventUserId(int event_user_id)
    { this.event_user_id = event_user_id;}

    public void setEventLocationId(int event_location_id)
    { this.event_location_id = event_location_id; }

    public void setLatitude(double event_latitude)
    { this.event_latitude = event_latitude; }

    public void setLongitude(double event_longitude)
    { this.event_longitude = event_longitude; }

    public void setEventDateTime(String event_date_time)
    { this.event_date_time = event_date_time; }


    public int getEventId(){
        return event_id;
    }

    public int getEventPeriodId()
    { return event_period_id; }

    public int getEventUserID()
    { return event_user_id; }

    public int getEventLocationId()
    { return event_location_id; }

    public double getEventLatitude()
    { return event_latitude; }

    public double getEventLongitude()
    { return event_longitude; }

    public String getEventDateTime()
    { return event_date_time; }

}