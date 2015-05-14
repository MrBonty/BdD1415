package bd1415.unipd.dei.it.cardb;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SpinnerDialog<E> extends AlertDialog {

    final boolean isPrimary = Builder.isPrimary;
    final TextView textview = Builder.view;
    String s1, s2, s3;
    String oldValue = Builder.oldValue;
    View view;
    ArrayAdapter<E> adapter;
    boolean isPresent = Builder.isPresent;

    public SpinnerDialog(final Context context) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        if (isPresent) {
            view = layoutInflater.inflate(R.layout.spinner_dialog, null);
        } else {
            view = layoutInflater.inflate(R.layout.spinner_add_dialog, null);
        }
        this.view = view;
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
        Button cancel = (Button) view.findViewById(R.id.spinner_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void prepareList() {
        final ListView list = (ListView) view.findViewById(R.id.spinner_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textview.setText(position + "");
                dismiss();
            }
        });
    }


    public void setAdapter(ArrayAdapter<E> adapter) {
        this.adapter = adapter;
    }

    public void setMarca() {
        //final EditText spinnervalue = (EditText) view.findViewById(R.id.spinner_field1);
        //final EditText editvalue = (EditText) view.findViewById(R.id.spinner_field2);
        //final EditText editvalue = (EditText) view.findViewById(R.id.spinner_field3);


        //editvalue.setText(oldValue);
    }

    public static class Builder<E> {

        public static String oldValue;
        static boolean isPrimary;
        static Context context;
        static TextView view;
        static boolean isPresent;
        ArrayAdapter<E> adapter;

        public Builder(String oldValue, final boolean isPrimary, final Context context, final TextView view, ArrayAdapter<E> adapter, boolean isPresent) {
            this.oldValue = oldValue;
            this.isPrimary = isPrimary;
            this.context = context;
            this.view = view;
            this.adapter = adapter;
            this.isPresent = isPresent;
        }

        public SpinnerDialog build() {
            SpinnerDialog tm = new SpinnerDialog(context);
            tm.setAdapter(adapter);
            tm.prepareList();
            return tm;
        }

        public SpinnerDialog buildMarca() {
            SpinnerDialog tm = new SpinnerDialog(context);
            tm.setAdapter(adapter);
            tm.prepareList();
            tm.setMarca();
            return tm;
        }
    }
}
