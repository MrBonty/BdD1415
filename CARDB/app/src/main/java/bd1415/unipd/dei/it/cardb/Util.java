package bd1415.unipd.dei.it.cardb;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class Util {

    public static final String DIVISOR = "##";
    private static String[] output;
    private static boolean isSet = false;

    private static final String DATE_DIVISOR = "/";

    public static String[] process(String result) {
        String[] tmp = result.split(DIVISOR);
        return tmp;
    }


    public static boolean compareDate(String date, String lavoroDate){ //TODO method return true if
        if(lavoroDate == null){
            return false;
        }
        if(date == null || date.equals("")){
            return true;
        }

        //TODO set date
        int[] tmp = splitDate(date);
        int day = tmp[0];
        int month = tmp[1];
        int year = tmp[2];

        tmp = splitDate(lavoroDate);
        int dayLavoro = tmp[0];
        int monthLavoro = tmp[1];
        int yearLavoro = tmp[2];

        Date dt = new Date(year,month,day);
        Date dtLavoro =new Date(yearLavoro,monthLavoro,dayLavoro);

        if(dt.compareTo(dtLavoro)>0){
            return true;
        }

        return false;
    }

    public static String getDate(){
        Calendar cal = Calendar.getInstance();
        String tmp= cal.get(Calendar.DAY_OF_MONTH) + DATE_DIVISOR
                + (cal.get(Calendar.MONTH)+1) + DATE_DIVISOR
                + cal.get(Calendar.YEAR);

        return tmp;
    }

    private static int[] splitDate(String date){
        int[] tmp = new int[3];

        String[] tp = date.split(DATE_DIVISOR);
        tmp[0] = Integer.parseInt(tp[0]);
        tmp[1] = (Integer.parseInt(tp[1]))-1;
        tmp[2] = Integer.parseInt(tp[2]);
        return tmp;
    }
}
