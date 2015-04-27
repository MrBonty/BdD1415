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

    public static void setOutput(String result) {
        output = process(result);
        isSet = true;
    }

    public static String[] getOutput() {
        return output;
    }

    public static boolean isSet() {
        return isSet;
    }

    public static void setToNull() {
        isSet = false;
        output = null;
    }


    private static String[] process(String result) {
        String[] tmp = result.split(DIVISOR);
        return tmp;
    }


    private int mCorrectEnd = -1;

    public void expand(LinearLayout header, RelativeLayout expanded, TextView timer) {
        TextView time = timer;
        expanded.setVisibility(View.VISIBLE);
        time.setVisibility(View.GONE);

        final int heightStart = header.getHeight();
        final int heightFinal = expanded.getHeight();
        expanded.measure(0, heightFinal);
        header.measure(0, heightStart);
        ValueAnimator animator = slideAnimator(header.getMeasuredHeight(),
                expanded.getMeasuredHeight(), expanded);

        animator.start();
    }

    public void collapse(LinearLayout header, RelativeLayout expanded, TextView timer) {
        final RelativeLayout exp = expanded;
        final TextView time = timer;
        int finalHeight = expanded.getHeight();
        int startHeight = header.getHeight();
        ValueAnimator animator = slideAnimator(finalHeight, startHeight, expanded);

        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                exp.setVisibility(View.GONE);
                time.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
        animator.start();

    }


    private ValueAnimator slideAnimator(int start, int end, RelativeLayout expanded) {
        final RelativeLayout exp = expanded;

        if (mCorrectEnd == -1 && end != 0) {
            mCorrectEnd = end;
        }

        if (end != 0 && end > start) {
            end = mCorrectEnd;
        }
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = exp.getLayoutParams();
                layoutParams.height = value;
                exp.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

}
