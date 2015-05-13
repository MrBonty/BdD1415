package bd1415.unipd.dei.it.cardb;

import android.app.AlertDialog;
import android.app.Dialog;
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

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class DialogInsertCliente extends Dialog{

    private Context ctx;
    private ViewHolder viewHolder;
    private View view;

    public DialogInsertCliente(final Context context) {
        super(context);

        ctx = context;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view= layoutInflater.inflate(R.layout.insert_cliente, null);


        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout dialogLayout = (LinearLayout) view.findViewById(R.id.ll_ins_cl);
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
        viewHolder.nome = (EditText) view.findViewById(R.id.cliente_nome_data);
        viewHolder.cognomeLayout = (LinearLayout) view.findViewById(R.id.cliete_cognome_layout);
        viewHolder.cognome = (EditText) view.findViewById(R.id.cliente_cognome_data);
        viewHolder.pkTag = (TextView) view.findViewById(R.id.cliente_pk_tag);
        viewHolder.pk = (EditText) view.findViewById(R.id.cliente_pk_data);
        viewHolder.telefono = (EditText) view.findViewById(R.id.cliente_telefono_data);
        viewHolder.citta = (EditText) view.findViewById(R.id.cliente_citta_data);
        viewHolder.provincia = (EditText) view.findViewById(R.id.cliente_provincia_data);
        viewHolder.indirizzo = (EditText) view.findViewById(R.id.cliente_indirizzo_data);
        viewHolder.numero_civico = (EditText) view.findViewById(R.id.cliente_civico_data);
        viewHolder.cancel = (Button) view.findViewById(R.id.edit_cancel);
        viewHolder.save = (Button) view.findViewById(R.id.edit_ok);

        viewHolder.title.setText("Nuovo cliente privato:");
        viewHolder.cognomeLayout.setVisibility(View.VISIBLE);
        viewHolder.pkTag.setText(R.string.cliente_cf_tag);

        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        viewHolder.save.setOnClickListener(saveListener());

        setContentView(view);
    }

    private View.OnClickListener saveListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCFnotUnique = false;

                String nome = viewHolder.nome.getText().toString();
                String cognome = viewHolder.cognome.getText().toString();
                String pk = viewHolder.pk.getText().toString();
                String telefono = viewHolder.telefono.getText().toString();
                String citta = viewHolder.citta.getText().toString();
                String provincia = viewHolder.provincia.getText().toString();
                String indirizzo = viewHolder.indirizzo.getText().toString();
                String num_civ = viewHolder.numero_civico.getText().toString();

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

                for (int i =0; i < ApplicationData.privati.size(); i++){
                    Privato tmp = ApplicationData.privati.get(i);
                    if(tmp.getCf().equals(pk)){
                        isCFnotUnique = true;
                        break;
                    }
                }

                if(isCFnotUnique){
                    Toast.makeText(ctx, "Codice Fiscale già presente", Toast.LENGTH_SHORT).show();
                }else if(nome == null || nome.equals("") ){
                    Toast.makeText(ctx, "Inserire nome cliente", Toast.LENGTH_SHORT).show();
                }else if(cognome == null || cognome.equals("")){
                    Toast.makeText(ctx, "Inserire cognome cliente", Toast.LENGTH_SHORT).show();
                }else if(pk == null || pk.equals("") || pk.length() != 16){
                    Toast.makeText(ctx, "Codice Fiscale non valido", Toast.LENGTH_SHORT).show();
                }else if(telefono == null || telefono.equals("")){
                    Toast.makeText(ctx, "Inserire numero di telefono", Toast.LENGTH_SHORT).show();
                }else {
                    Privato tmp = new Privato(pk ,true);
                    tmp.setNome(nome,true);
                    tmp.setCognome(cognome,true);
                    tmp.setTelefono(telefono,true);

                    AddressType ad =new AddressType();
                    ad.città = citta;
                    ad.provincia = provincia;
                    ad.indirizzo = indirizzo;
                    ad.numero_civico = num_civ;

                    tmp.setIndirizzo(ad,true);

                    ApplicationData.privati.add(tmp);
                    PrivatiMenuFragment.list.notifyDataSetChanged();

                    dismiss();
                }
            }
        };
    }

    private class ViewHolder{
        public TextView title;
        public EditText nome;
        public LinearLayout cognomeLayout;
        public EditText cognome;
        public TextView pkTag;
        public EditText pk;
        public EditText telefono;
        public EditText citta;
        public EditText provincia;
        public EditText indirizzo;
        public EditText numero_civico;
        public Button cancel;
        public Button save;
    }
}
