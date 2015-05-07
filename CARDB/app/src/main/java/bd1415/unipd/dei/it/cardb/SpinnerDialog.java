package bd1415.unipd.dei.it.cardb;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerDialog extends AlertDialog {

    String s1, s2, s3;
    String oldValue = Builder.oldValue;
    final boolean isPrimary = Builder.isPrimary;
    final TextView textview = Builder.view;

    public SpinnerDialog(final Context context) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_dialog, null);
        setView(view);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout dialogLayout = (LinearLayout) view.findViewById(R.id.spinner_dialog);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        setCanceledOnTouchOutside(false);
        TextView dialogName = (TextView) view.findViewById(R.id.spinner_title);
        dialogName.setText(R.string.spinner);
        final EditText value1 = (EditText) view.findViewById(R.id.spinner_field1);
        final EditText value2 = (EditText) view.findViewById(R.id.spinner_field2);
        final EditText value3 = (EditText) view.findViewById(R.id.spinner_field1);
        Button ok = (Button) view.findViewById(R.id.spinner_ok);
        Button cancel = (Button) view.findViewById(R.id.spinner_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = value1.getText().toString();
                s2 = value2.getText().toString();
                s3 = value3.getText().toString();
                if ((s1.equals("") || s1 == null) && isPrimary) {
                    Toast.makeText(context, "Inserire la chiave primaria!", Toast.LENGTH_SHORT).show();
                } else {
                    textview.setText(s1 + " " + s2 + " " + s3);
                    dismiss();
                }
            }
        });
        final ListView list = (ListView) view.findViewById(R.id.spinner_list);
        //TODO list managment
    }

    public static class Builder {

        public static String oldValue;
        static boolean isPrimary;
        static Context context;
        static TextView view;

        public Builder(String oldValue, final boolean isPrimary, final Context context, final TextView view) {
            this.oldValue = oldValue;
            this.isPrimary = isPrimary;
            this.context = context;
            this.view = view;

        }

        public SpinnerDialog build() {
            return new SpinnerDialog(context);
        }
    }
}
