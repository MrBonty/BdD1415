package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class ClientiBodyFragment extends Fragment {

    private ViewHolder viewHolder;

    private int mPos = -1;
    private boolean mIsVis = false;
    private boolean mIsPrivate = false;
    private ImageView mImage;
    private LinearLayout mBody;

    //onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    //onActivityCreated
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        if (savedInstanceState != null) {
            mPos = savedInstanceState.getInt(ClientiMenuFragment.POS);
            mIsVis = savedInstanceState.getBoolean(ClientiMenuFragment.ISVIS);
            mIsPrivate = savedInstanceState.getBoolean(ClientiMenuFragment.ISP);
        }*/

        View view = inflater.inflate(R.layout.clienti_body_fragment, container, false);

        viewHolder = new ViewHolder();
            if (mIsVis) {
                mImage = (ImageView) view.findViewById(R.id.image_clienti);
                mImage.setVisibility(View.GONE);
                mBody = (LinearLayout) view.findViewById(R.id.ll_clienti);
                mBody.setVisibility(View.VISIBLE);
            }
            viewHolder.nome = (TextView) view.findViewById(R.id.cliente_nome_data);
            viewHolder.cognomeLayout = (LinearLayout) view.findViewById(R.id.cliete_cognome_layout);
            viewHolder.cognome = (TextView) view.findViewById(R.id.cliente_cognome_data);
            viewHolder.pkTag = (TextView) view.findViewById(R.id.cliente_pk_tag);
            viewHolder.pk = (TextView) view.findViewById(R.id.cliente_nome_tag);
            viewHolder.telefono = (TextView) view.findViewById(R.id.cliente_telefono_data);
            viewHolder.citta = (TextView) view.findViewById(R.id.cliente_citta_data);
            viewHolder.provincia = (TextView) view.findViewById(R.id.cliente_provincia_data);
            viewHolder.indirizzo = (TextView) view.findViewById(R.id.cliente_indirizzo_data);
            viewHolder.veicoli = (ListView) view.findViewById(android.R.id.list);
            view.setTag(viewHolder);

        if (mIsVis) {
            if (mIsPrivate) {
                Privato cl = ApplicationData.privati.get(mPos);

                viewHolder.nome.setText(cl.getNome());
                viewHolder.cognomeLayout.setVisibility(View.VISIBLE);
                viewHolder.cognome.setText(cl.getCognome());

                viewHolder.pkTag.setText(R.string.cliente_cf_tag);
                viewHolder.pk.setText(cl.getCf());

                viewHolder.telefono.setText(cl.getTelefono());
                if(cl.getIndirizzo() != null) {
                    viewHolder.indirizzo.setText(cl.getIndirizzo().indirizzo + " " + cl.getIndirizzo().numero_civico);
                    viewHolder.citta.setText(cl.getIndirizzo().città);
                    viewHolder.provincia.setText(cl.getIndirizzo().provincia);
                }
                ArrayList<Veicolo> tmp = new ArrayList<>();
                for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                    Veicolo vl = ApplicationData.veicoli.get(i);
                    if (vl.getPrivato().equals(cl.getCf())) {
                        tmp.add(vl);
                    }
                }

                viewHolder.veicoli.setAdapter(new VeicoliArrayAdapter(MainActivity.ctx, tmp));

            } else {
                Azienda az = ApplicationData.aziende.get(mPos);

                viewHolder.nome.setText(az.getNome());
                viewHolder.cognomeLayout.setVisibility(View.GONE);

                viewHolder.pkTag.setText(R.string.cliente_piva_tag);
                viewHolder.pk.setText(az.getPiva());

                viewHolder.telefono.setText(az.getTelefono());

                viewHolder.indirizzo.setText(az.getIndirizzo().indirizzo + " " + az.getIndirizzo().numero_civico);
                viewHolder.citta.setText(az.getIndirizzo().città);
                viewHolder.provincia.setText(az.getIndirizzo().provincia);

                ArrayList<Veicolo> tmp = new ArrayList<>();
                for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                    Veicolo vl = ApplicationData.veicoli.get(i);
                    if (vl.getAzienda().equals(az.getPiva())) {
                        tmp.add(vl);
                    }
                }

                viewHolder.veicoli.setAdapter(new VeicoliArrayAdapter(MainActivity.ctx, tmp));
            }
        }
        return view;
    }

    public static ClientiBodyFragment newIstance(boolean isPrivate, boolean isVis, int pos){
        ClientiBodyFragment tmp = new ClientiBodyFragment();
        tmp.mIsPrivate = isPrivate;
        tmp.mIsVis = isVis;
        tmp.mPos = pos;

        return tmp;
    }

    private class ViewHolder {
        public TextView nome;
        public LinearLayout cognomeLayout;
        public TextView cognome;
        public TextView pkTag;
        public TextView pk;
        public TextView telefono;
        public TextView citta;
        public TextView provincia;
        public TextView indirizzo;
        public ListView veicoli;
    }
}
