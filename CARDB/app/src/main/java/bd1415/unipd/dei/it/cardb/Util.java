package bd1415.unipd.dei.it.cardb;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Util {

    public static final String DIVISOR = "##";
    private static String[] output;
    private static boolean isSet = false;

    public static String[] process(String result) {
        String[] tmp = result.split(DIVISOR);
        return tmp;
    }


}
