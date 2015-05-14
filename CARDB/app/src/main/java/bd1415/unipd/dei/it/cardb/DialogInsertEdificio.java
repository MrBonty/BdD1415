package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Edificio;

public class DialogInsertEdificio extends Dialog {//TODO

    private Context ctx;
    private ViewHolder viewHolder;
    private View view;

    public DialogInsertEdificio(Context context) {
        super(context);
        ctx = context;

        viewHolder.tipologia = (EditText) view.findViewById(R.id.edificio_tip_data);
        viewHolder.citta = (EditText) view.findViewById(R.id.edificio_citta_data);
        viewHolder.indirizzo = (EditText) view.findViewById(R.id.edificio_indirizzo_data);
        viewHolder.provincia = (EditText) view.findViewById(R.id.edificio_provincia_data);
        viewHolder.numero_civico = (EditText) view.findViewById(R.id.edificio_civico_data);
        viewHolder.cancel = (Button) view.findViewById(R.id.edit_cancel);
        viewHolder.save = (Button) view.findViewById(R.id.edit_ok);

        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        viewHolder.save.setOnClickListener(saveListener());

        setContentView(view);
    }

    private View.OnClickListener saveListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tipologia = viewHolder.tipologia.getText().toString();
                String citta = viewHolder.citta.getText().toString();
                String provincia = viewHolder.provincia.getText().toString();
                String indirizzo = viewHolder.indirizzo.getText().toString();
                String num_civ = viewHolder.numero_civico.getText().toString();

                if (citta == null) {
                    citta = "";
                }
                if (provincia == null) {
                    provincia = "";
                }
                if (indirizzo == null) {
                    indirizzo = "";
                }
                if (num_civ == null) {
                    num_civ = "";
                }

                if (tipologia == null || tipologia.equals("")) {
                    Toast.makeText(ctx, "Tipologia Edificio non valida", Toast.LENGTH_SHORT).show();
                } else {
                    Edificio ed = new Edificio(tipologia, true);

                    AddressType ad = new AddressType();
                    ad.città = citta;
                    ad.provincia = provincia;
                    ad.indirizzo = indirizzo;
                    ad.numero_civico = num_civ;

                    ed.setIndirizzo(ad, true);

                    ApplicationData.edifici.add(ed);
                    EdificiMenuFragment.list.notifyDataSetChanged();

                    dismiss();
                }
            }
        };
    }

    private class ViewHolder {
        public EditText tipologia;
        public EditText citta;
        public EditText provincia;
        public EditText indirizzo;
        public EditText numero_civico;
        public Button cancel;
        public Button save;
    }
}
