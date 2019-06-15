package data.personal.coursefinalversionfour;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by ilma on 26/04/2017.
 */

public class BarChartEntry {
    private Date date;
    public int value;
    private String month;
    private String day;
    private int id = 0;
    public void BarChartEntry(){}

    public void BarChartEntry(Date date,int value,String month,String day){
        this.date = date;
        this.value = value;
        this.month = month;
        this.day = day;
    }
    public void BarChartEntry(Date date,int value){
        this.date = date;
        this.value = value;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public Date getDate(){
        return this.date;
    }
    public String getMonth(){
        return this.month;
    }
    public String getDay(){
        return this.day;
    }
    public void setMonth(String month){
        this.month=month;
    }
    public void setDay(String day){
        this.day = day;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

}
class WeeklyDataIdComparator implements Comparator<BarChartEntry> {
    public int compare(BarChartEntry entry1, BarChartEntry entry2) {
        return entry1.getId() - entry2.getId();
    }
}