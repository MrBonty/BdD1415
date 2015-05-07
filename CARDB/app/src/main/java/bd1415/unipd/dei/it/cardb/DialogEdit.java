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
import android.widget.TextView;
import android.widget.Toast;

public class DialogEdit extends AlertDialog {

    String s;
    String oldValue = Builder.oldValue;
    final boolean isPrimary = Builder.isPrimary;
    final TextView textview = Builder.view;

    public DialogEdit(final Context context) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.edit_dialog, null);
        setView(view);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout dialogLayout = (LinearLayout) view.findViewById(R.id.edit_dialog);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        setCanceledOnTouchOutside(false);
        TextView dialogName = (TextView) view.findViewById(R.id.edit_title);
        dialogName.setText(R.string.edit);
        final EditText editvalue = (EditText) view.findViewById(R.id.edit_field);
        editvalue.setText(oldValue);
        Button ok = (Button) view.findViewById(R.id.edit_ok);
        Button cancel = (Button) view.findViewById(R.id.edit_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = editvalue.getText().toString();
                if ((s.equals("") || s == null) && isPrimary) {
                    Toast.makeText(context, "Inserire la chiave primaria!", Toast.LENGTH_SHORT).show();
                } else {
                    textview.setText(s);
                    dismiss();
                }
            }
        });
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

        public DialogEdit build() {
            return new DialogEdit(context);
        }
    }

}
