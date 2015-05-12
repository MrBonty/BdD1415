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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerDialogV2<E> extends AlertDialog {


    View view;
    ArrayAdapter<E> adapter;
    boolean isPresent = Builder.isPresent;
    String s;

    public SpinnerDialogV2(final Context context) {
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
                s = position + "";
                ApplicationData.positionLavoriDialogInsert = position;
                dismiss();
            }
        });
    }

    public void setS(String s) {
        this.s = s;
    }


    public void setAdapter(ArrayAdapter<E> adapter) {
        this.adapter = adapter;
    }

    public static class Builder<E> {

        ArrayAdapter<E> adapter;
        static Context context;
        static boolean isPresent;
        String s;

        public Builder(String s, final Context context, ArrayAdapter<E> adapter, boolean isPresent) {
            this.context = context;
            this.adapter = adapter;
            this.isPresent = isPresent;
            this.s = s;
        }

        public SpinnerDialogV2 build() {
            SpinnerDialogV2 tm = new SpinnerDialogV2(context);
            tm.setAdapter(adapter);
            tm.prepareList();
            tm.setS(s);
            return tm;
        }

        public SpinnerDialogV2 buildMarca() {
            SpinnerDialogV2 tm = new SpinnerDialogV2(context);
            tm.setAdapter(adapter);
            tm.prepareList();

            return tm;
        }
    }
}

