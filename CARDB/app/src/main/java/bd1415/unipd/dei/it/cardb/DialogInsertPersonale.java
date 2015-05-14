package bd1415.unipd.dei.it.cardb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Personale;

public class DialogInsertPersonale extends Dialog {

    private Context ctx;
    private ViewHolder viewHolder;
    private View view;

    private Edificio toAdd;

    public DialogInsertPersonale(Context context) {
        super(context);

        ctx = context;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view= layoutInflater.inflate(R.layout.insert_personale, null);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout dialogLayout = (LinearLayout) view.findViewById(R.id.ll_ins_per);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        setCanceledOnTouchOutside(false);

        viewHolder = new ViewHolder();

        viewHolder.title = (TextView) view.findViewById(R.id.edit_title);
        viewHolder.nome = (EditText) view.findViewById(R.id.personale_nome_data);
        viewHolder.cognome = (EditText) view.findViewById(R.id.personale_cognome_data);
        viewHolder.cf = (EditText) view.findViewById(R.id.personale_cf_data);
        viewHolder.iban = (EditText) view.findViewById(R.id.personale_iban_data);
        viewHolder.contratto = (EditText) view.findViewById(R.id.personale_contratto_data);
        viewHolder.telefono = (EditText) view.findViewById(R.id.personale_telefono_data);
        viewHolder.citta = (EditText) view.findViewById(R.id.personale_citta_data);
        viewHolder.provincia = (EditText) view.findViewById(R.id.personale_provincia_data);
        viewHolder.indirizzo = (EditText) view.findViewById(R.id.personale_indirizzo_data);
        viewHolder.numero_civico = (EditText) view.findViewById(R.id.personale_civico_data);
        viewHolder.edificio = (TextView) view.findViewById(R.id.personale_edificio_data);
        viewHolder.add_ed = (Button) view.findViewById(R.id.add_edificio);
        viewHolder.cancel = (Button) view.findViewById(R.id.edit_cancel);
        viewHolder.save = (Button) view.findViewById(R.id.edit_ok);

        viewHolder.add_ed.setOnClickListener(addEdListener());
        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        viewHolder.save.setOnClickListener(saveListener());


        setContentView(view);
    }

    private View.OnClickListener addEdListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.edificio.getText().toString(),
                        true, MainActivity.ctx, viewHolder.edificio, new EdificiArrayAdapter(MainActivity.ctx, ApplicationData.edifici), true).build();
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                @Override
                                                public void onDismiss(DialogInterface arg0) {
                                                    try {
                                                        Edificio ed = ApplicationData.edifici.get(Integer.parseInt(viewHolder.edificio.getText().toString()));
                                                        viewHolder.edificio.setText(ed.getId() + " " + ed.getTipologia());
                                                        toAdd = ed;
                                                    } catch (java.lang.NumberFormatException ex) {

                                                    }
                                                }
                                            }
                );
            }
        };
    }



    private View.OnClickListener saveListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCFnotUnique = false;

                String nome = viewHolder.nome.getText().toString();
                String cognome = viewHolder.cognome.getText().toString();
                String cf = viewHolder.cf.getText().toString();
                String telefono = viewHolder.telefono.getText().toString();
                String citta = viewHolder.citta.getText().toString();
                String provincia = viewHolder.provincia.getText().toString();
                String indirizzo = viewHolder.indirizzo.getText().toString();
                String num_civ = viewHolder.numero_civico.getText().toString();
                String contratto = viewHolder.contratto.getText().toString();
                String iban = viewHolder.iban.getText().toString();

                if(citta == null){
                    citta = "";
                }
                if(provincia == null){
                    provincia = "";
                }
                if(indirizzo == null){
                    indirizzo = "";
                }
                if(num_civ == null){
                    num_civ = "";
                }

                for (int i =0; i < ApplicationData.personale.size(); i++){
                    Personale tmp = ApplicationData.personale.get(i);
                    if(tmp.getCf().equals(cf)){
                        isCFnotUnique = true;
                        break;
                    }
                }

                if(isCFnotUnique){
                    Toast.makeText(ctx, "Codice Fiscale già presente", Toast.LENGTH_SHORT).show();
                }else if(toAdd == null){
                    Toast.makeText(ctx, "Assegnare Edificio", Toast.LENGTH_SHORT).show();
                }else if(nome == null || nome.equals("") ){
                    Toast.makeText(ctx, "Inserire nome lavoratore", Toast.LENGTH_SHORT).show();
                }else if(cognome == null || cognome.equals("")){
                    Toast.makeText(ctx, "Inserire cognome lavoratore", Toast.LENGTH_SHORT).show();
                }else if(cf == null || cf.equals("") || cf.length() != 16){
                    Toast.makeText(ctx, "Codice Fiscale non valido", Toast.LENGTH_SHORT).show();
                }else if(telefono == null || telefono.equals("")) {
                    Toast.makeText(ctx, "Inserire numero di telefono", Toast.LENGTH_SHORT).show();
                }else if(contratto == null || contratto.equals("")) {
                    Toast.makeText(ctx, "Inserire tipo contratto", Toast.LENGTH_SHORT).show();
                }else if(iban == null || iban.equals("")) {
                    Toast.makeText(ctx, "Inserire iban", Toast.LENGTH_SHORT).show();
                }else {
                    Personale tmp = new Personale(cf , toAdd.getId(), true);
                    tmp.setNome(nome,true);
                    tmp.setCognome(cognome,true);
                    tmp.setTelefono(telefono,true);
                    tmp.setResponsabile(0, true);
                    tmp.setContratto(contratto, true);
                    tmp.setIban(iban, true);

                    AddressType ad =new AddressType();
                    ad.città = citta;
                    ad.provincia = provincia;
                    ad.indirizzo = indirizzo;
                    ad.numero_civico = num_civ;

                    tmp.setIndirizzo(ad,true);

                    ApplicationData.personale.add(tmp);
                    PersonaleMenuFragment.list.notifyDataSetChanged();

                    dismiss();
                }
            }
        };
    }

    private class ViewHolder{
        public TextView title;
        public EditText nome;
        public EditText cognome;
        public EditText cf;
        public EditText iban;
        public EditText contratto;
        public EditText telefono;
        public EditText citta;
        public EditText provincia;
        public EditText indirizzo;
        public EditText numero_civico;
        public TextView edificio;
        public Button add_ed;
        public Button cancel;
        public Button save;
    }
}
