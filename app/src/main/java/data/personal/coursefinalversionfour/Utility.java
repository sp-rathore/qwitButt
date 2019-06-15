package data.personal.coursefinalversionfour;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ilma on 22/03/2017.
 */

class Utility {
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String getDateFormatted(Date dt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(dt);
    }
    public static String getDateOnlyFormatted(Date dt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(dt);
    }
    public static int getDayPeriod(){
        Calendar c = Calendar.getInstance();
        int Hr24=c.get(Calendar.HOUR_OF_DAY);
        if(Hr24 >= 4 && Hr24<=9)
              return 1;
        else if(Hr24 > 9 && Hr24<=12)
            return 2;
        else if(Hr24 > 12 && Hr24<=16)
            return 3;
        else if(Hr24 > 16 && Hr24<=20)
            return 4;
        else
            return 5;

    }
    public static int getLocation(String location){

        if(location.equals("HOME"))
            return 1;
        else if(location.equals("OFFICE"))
            return 2;
        else if(location.equals("CAR"))
            return 3;
        else if(location.equals("WALK"))
            return 4;
        else if(location.equals("SCHOOL"))
            return 5;
        else
            return 6;

    }
}
