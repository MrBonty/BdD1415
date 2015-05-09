package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Lavora_a;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Personale;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class PersonaleBodyFragment extends Fragment{

    private ViewHolder viewHolder = null;

    private int mPos = -1;
    private boolean mIsVis = false;
    private ImageView mImage;
    private LinearLayout mBody;

    //onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(PrivatiMenuFragment.POS);
            mIsVis = args.getBoolean(PrivatiMenuFragment.ISVIS);
        }

        final View view = inflater.inflate(R.layout.personale_body_fragment, container, false);

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_personale);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_personale);
            mBody.setVisibility(View.VISIBLE);
        }else {
            mImage = (ImageView) view.findViewById(R.id.image_clienti);
            mBody = (LinearLayout) view.findViewById(R.id.ll_clienti);
            if(mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }
        viewHolder.nome = (TextView) view.findViewById(R.id.personale_nome_data);
        viewHolder.cognome = (TextView) view.findViewById(R.id.personale_cognome_data);
        viewHolder.cf = (TextView) view.findViewById(R.id.personale_cf_tag);
        viewHolder.iban = (TextView) view.findViewById(R.id.personale_iban_data);
        viewHolder.contratto = (TextView) view.findViewById(R.id.personale_contratto_data);
        viewHolder.telefono = (TextView) view.findViewById(R.id.personale_telefono_data);
        viewHolder.citta = (TextView) view.findViewById(R.id.personale_citta_data);
        viewHolder.provincia = (TextView) view.findViewById(R.id.personale_provincia_data);
        viewHolder.indirizzo = (TextView) view.findViewById(R.id.personale_indirizzo_data);
        viewHolder.numero_civico = (TextView) view.findViewById(R.id.personale_civico_data);
        viewHolder.edificio = (TextView) view.findViewById(R.id.personale_edificio_data);
        viewHolder.responsabile = (CheckBox) view.findViewById(R.id.personale_responsabile_ckb);
        viewHolder.lavori = (ListView) view.findViewById(android.R.id.list);

        if (mIsVis) {
            final Personale per = ApplicationData.personale.get(mPos);

            ArrayList<String> item = new ArrayList<>();
            for(int i = 0; i< ApplicationData.lavora_a.size(); i++) {
                String ins = "";
                Lavora_a tmp = ApplicationData.lavora_a.get(i);
                boolean doIt = true;

                for(int j= 0; j< ApplicationData.lavoriInCorso.size(); j++){
                    Lavoro ll = ApplicationData.lavoriInCorso.get(j);
                    if(tmp.getLavoro() == ll.getId()){
                        ins = ll.getId() + " " + ll.getData_inizio() + " ore lav: ";
                        ins += tmp.getOre_lavoro() + " straordinari: " + tmp.getStraordinari();
                    }
                }
                for (int j = 0; j<ApplicationData.lavoriFiniti.size() && doIt; j++){
                    Lavoro ll = ApplicationData.lavoriFiniti.get(j);
                    if(tmp.getLavoro() == ll.getId()){
                        ins = ll.getId() + " " + ll.getData_inizio() + " ~ " + ll.getData_fine() + " ore lav: ";
                        ins += tmp.getOre_lavoro() + " straordinari " + tmp.getStraordinari();
                    }
                }

                item.add(ins);
            }

            viewHolder.nome.setText(per.getNome());
            viewHolder.cognome.setText(per.getCognome());
            viewHolder.cf.setText(per.getCf());
            viewHolder.iban.setText(per.getIban());
            viewHolder.contratto.setText(per.getContratto());
            viewHolder.telefono.setText(per.getTelefono());

            for(int i= 0; i<ApplicationData.edifici.size(); i++) {
                Edificio tmp = ApplicationData.edifici.get(i);
                if (tmp.getId() == per.getEdificio()) {
                    String ss = tmp.getId()+ " " + tmp.getTipologia();
                    viewHolder.edificio.setText(ss);
                    break;
                }
            }

            if(per.getResponsabile() == 0) {
                viewHolder.responsabile.setChecked(false);
            }else {
                viewHolder.responsabile.setChecked(true);
            }
            viewHolder.responsabile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        for(int i = 0; i< ApplicationData.personale.size(); i++){
                            final Personale tmp = ApplicationData.personale.get(i);
                            if(tmp.getEdificio() == per.getEdificio() && tmp.getResponsabile() != 0){
                                final Dialog dial = new Dialog(getActivity().getBaseContext());
                                dial.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dial.setContentView(R.layout.dialog_change);

                                TextView tx = (TextView) dial.findViewById(R.id.title);
                                TextView oldW = (TextView) dial.findViewById(R.id.old_per);
                                TextView newW = (TextView) dial.findViewById(R.id.new_per);
                                Button cancel = (Button) dial.findViewById(R.id.cancel);
                                Button change = (Button) dial.findViewById(R.id.add_to);
                                change.setText("Change");

                                tx.setText("Vuoi cambiare responsabile:");
                                oldW.setText("Vecchio Resp = " + tmp.getCognome() + " " + tmp.getNome() + " " + tmp.getCf());
                                oldW.setText("Nuovo Resp = " + per.getCognome() + " " + per.getNome() + " " + per.getCf());

                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dial.dismiss();
                                    }
                                });
                                change.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        per.setResponsabile(1, true);
                                        tmp.setResponsabile(0, true);
                                        viewHolder.responsabile.setChecked(true);
                                        dial.dismiss();
                                    }
                                });

                                dial.show();
                            }
                        }

                    }else{
                        per.setResponsabile(0, true);
                    }
                }
            });

            if(per.getIndirizzo() != null){
                viewHolder.citta.setText(per.getIndirizzo().città);
                viewHolder.provincia.setText(per.getIndirizzo().provincia);
                viewHolder.indirizzo.setText(per.getIndirizzo().indirizzo);
                viewHolder.numero_civico.setText(per.getIndirizzo().numero_civico);
            }

            viewHolder.lavori.setAdapter(new LavoraAArrayAdapter(MainActivity.ctx, item));
        }
        return view;
    }

    private class ViewHolder {
        public TextView nome;
        public TextView cognome;
        public TextView cf;
        public TextView iban;
        public TextView contratto;
        public TextView telefono;
        public TextView citta;
        public TextView provincia;
        public TextView indirizzo;
        public TextView numero_civico;
        public TextView edificio;
        public CheckBox responsabile;
        public ListView lavori;
    }

}
