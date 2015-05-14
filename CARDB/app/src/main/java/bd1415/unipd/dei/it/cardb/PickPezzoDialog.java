package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Pezzo;

public class PickPezzoDialog extends Dialog {


    boolean isSet = false;
    private Context ctx;
    private View view;
    private Spinner spinner;
    private NumberPicker numberPicker;

    private int pos;


    public PickPezzoDialog(final Context context) {
        super(context);
        ctx = context;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.spin_pick_dialog, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout dialogLayout = (LinearLayout) view.findViewById(R.id.spin_pick_ll);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        setCanceledOnTouchOutside(false);

        final Button save = (Button) view.findViewById(R.id.edit_ok);
        save.setEnabled(false);
        Button cancel = (Button) view.findViewById(R.id.edit_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        numberPicker = (NumberPicker) view.findViewById(R.id.pic_data);
        numberPicker.setEnabled(false);

        ArrayList<String> forSpinner = new ArrayList<>();
        for (int i = 0; i < ApplicationData.pezzi.size(); i++) {
            Pezzo tmp = ApplicationData.pezzi.get(i);
            forSpinner.add(tmp.getId() + "P - " + tmp.getDescrizione());
        }

        spinner = (Spinner) view.findViewById(R.id.spin_data);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, forSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pezzo pe = ApplicationData.pezzi.get(position);
                pos = position;

                numberPicker.setEnabled(true);
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(pe.getNumero_totale_pezzi());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                numberPicker.setEnabled(false);
                save.setEnabled(false);
            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal > 0) {
                    save.setEnabled(true);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationData.positionLavoriDialogInsert = pos;
                ApplicationData.quantità = numberPicker.getValue();

                dismiss();
            }
        });

        setContentView(view);
    }

}

