package in.co.sattamaster.ui.DateTime.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Converter {

    public static String formatDate(Date chosenDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // Set your date format
        return sdf.format(chosenDate);
    }

    public static String formatDateHuman(Date chosenDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM, yy"); // Set your date format
        return sdf.format(chosenDate);
    }

    public static String formatTime(Calendar time){

        int hour = time.get(Calendar.HOUR_OF_DAY);
        int min = time.get(Calendar.MINUTE);
        boolean pm = false;

        String curTime = String.format("%02d:%02d", hour, min);

      /*  if(hour>12){
            pm = true;
            hour = hour + 12;
        } */

       // return pm ? hour +":"+min : hour +":"+min;
        return curTime;
    }
}
