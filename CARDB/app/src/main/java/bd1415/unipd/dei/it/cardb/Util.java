package bd1415.unipd.dei.it.cardb;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;

public class Util {

    public static final String DIVISOR = "##";
    private static String[] output;
    private static boolean isSet = false;

    private static final String DATE_DIVISOR = "-";
    private static final String TIME_DIVISOR = ":";

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
        int day = tmp[2];
        int month = tmp[1];
        int year = tmp[0];

        tmp = splitDate(lavoroDate);
        int dayLavoro = tmp[2];
        int monthLavoro = tmp[1];
        int yearLavoro = tmp[0];

        Date dt = new Date(year,month,day);
        Date dtLavoro =new Date(yearLavoro,monthLavoro,dayLavoro);

        if(dt.compareTo(dtLavoro)>0){
            return true;
        }

        return false;
    }

    public static String getDate(){
        Calendar cal = Calendar.getInstance();
        String tmp= cal.get(Calendar.YEAR) + DATE_DIVISOR
                + (cal.get(Calendar.MONTH)+1) + DATE_DIVISOR
                + cal.get(Calendar.DAY_OF_MONTH);

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

    public static String getTimeDate(){
        Calendar cal = Calendar.getInstance();
        String tmp= cal.get(Calendar.YEAR) + DATE_DIVISOR
                + (cal.get(Calendar.MONTH)+1) + DATE_DIVISOR
                + cal.get(Calendar.DAY_OF_MONTH)/*+ " "
                + cal.get(Calendar.HOUR_OF_DAY) + TIME_DIVISOR
                + cal.get(Calendar.MINUTE) + TIME_DIVISOR
                + cal.get(Calendar.SECOND)*/;

        return tmp;
    }

    public static AddressType getAddress(String addSql){
        Log.i("STRINGA", addSql);
        int min = 0;
        int max = addSql.length();
        char c = '(';
        char e = ')';
        for (int i = 0; i<addSql.length(); i++){
            if(c == addSql.charAt(i)){
                min= i+1;
            }
            if(e == addSql.charAt(i)){
                max = i;
            }
        }

        addSql = addSql.substring(min,max);

        String[] tmp = addSql.split(",");

        String città = tmp[2];
        String indirizzo = tmp[0];
        String numero_civico = tmp[1];
        String provincia = tmp[3];

        char ap = '"';
        char sp = ' ';

        min = 0;
        max = città.length();

        for(int i = 0; i< città.length(); i++){
            if(ap == città.charAt(i)){
                int j = i;
                for(j= i+1; j<città.length()&& sp==città.charAt(j);j++);
                min = j;
                break;
            }
        }
        for(int i = città.length()-1; i>= min; i--){
            if(ap == città.charAt(i)){

                max = i;
                break;
            }
        }
        città = città.substring(min,max);

        min = 0;
        max = indirizzo.length();
        for(int i = 0; i< indirizzo.length(); i++){
            if(ap == indirizzo.charAt(i)){
                int j = i;
                for(j= i+1; j<indirizzo.length()&& sp==indirizzo.charAt(j);j++);
                min = j;
                break;
            }
        }
        for(int i = indirizzo.length()-1; i>= min; i--){
            if(ap == indirizzo.charAt(i)){
                max = i;
                break;
            }
        }
        indirizzo = indirizzo.substring(min,max);

        min = 0;
        max = numero_civico.length();
        for(int i = 0; i< numero_civico.length(); i++){
            if(ap == numero_civico.charAt(i)){
                int j = i;
                for(j= i+1; j<numero_civico.length()&& sp==numero_civico.charAt(j);j++);
                min = j;
                break;
            }
        }
        for(int i = numero_civico.length()-1; i>= min; i--){
            if(ap == numero_civico.charAt(i)){
                max = i;
                break;
            }
        }
        numero_civico = numero_civico.substring(min,max);

        min = 0;
        max = provincia.length();
        for(int i = 0; i< provincia.length(); i++){
            if(ap == provincia.charAt(i)){
                int j = i;
                for(j= i+1; j<provincia.length()&& sp==provincia.charAt(j);j++);
                min = j;
                break;
            }
        }
        for(int i = provincia.length()-1; i>= min; i--){
            if(ap == provincia.charAt(i)){
                max = i;
                break;
            }
        }
        provincia = provincia.substring(min,max);

        AddressType a = new AddressType();
        a.città = città;
        a.indirizzo = indirizzo;
        a.numero_civico = numero_civico;
        a.provincia = provincia;

        Log.i("STRINGA2", a.città);
        Log.i("STRINGA2", a.indirizzo);
        Log.i("STRINGA2", a.numero_civico);
        Log.i("STRINGA2", a.provincia);

        return a;
    }
}
