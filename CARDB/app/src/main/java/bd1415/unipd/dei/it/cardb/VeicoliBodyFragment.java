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

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Modello;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class VeicoliBodyFragment extends Fragment {

    private ViewHolder viewHolder;

    private ImageView mImage;
    private LinearLayout mBody;

    private int mPos = -1;
    private boolean mIsVis = false;

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

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(ClientiMenuFragment.POS);
            mIsVis = args.getBoolean(ClientiMenuFragment.ISVIS);
        }

        View view = inflater.inflate(R.layout.veicoli_body_fragment, container, false);

        viewHolder = new ViewHolder();

        if (mIsVis) {
            mImage = (ImageView) MainActivity.act.findViewById(R.id.image_veicoli);
            mBody = (LinearLayout) MainActivity.act.findViewById(R.id.ll_veicoli);
            mImage.setVisibility(View.GONE);
            mBody.setVisibility(View.VISIBLE);
        }

        viewHolder.targa = (TextView) view.findViewById(R.id.veicolo_targa_data);
        viewHolder.numero_telaio = (TextView) view.findViewById(R.id.veicolo_telaio_data);
        viewHolder.proprietario = (TextView) view.findViewById(R.id.veicolo_proprietario_data);
        viewHolder.marca = (TextView) view.findViewById(R.id.veicolo_modello_marca_data);
        viewHolder.modello = (TextView) view.findViewById(R.id.veicolo_modello_nome_data);
        viewHolder.anno_modello = (TextView) view.findViewById(R.id.veicolo_modello_anno_data);
        viewHolder.lavorazioni = (ListView) view.findViewById(android.R.id.list);
        view.setTag(viewHolder);

        if (mIsVis) {

            Veicolo cl = ApplicationData.veicoli.get(mPos);

            viewHolder.targa.setText(cl.getTarga());
            viewHolder.numero_telaio.setText(cl.getNumero_telaio());

            if (cl.getAzienda() != null && !cl.getAzienda().equals("")) {
                for (int i = 0; i < ApplicationData.aziende.size(); i++) {
                    Azienda tmp = ApplicationData.aziende.get(i);
                    if (tmp.getPiva().equals(cl.getAzienda())) {
                        viewHolder.proprietario.setText(tmp.getNome());
                        break;
                    }
                }
            } else {
                for (int i = 0; i < ApplicationData.privati.size(); i++) {
                    Privato tmp = ApplicationData.privati.get(i);
                    if (tmp.getCf().equals(cl.getPrivato())) {
                        viewHolder.proprietario.setText(tmp.getNome() + " " + tmp.getCognome());
                        break;
                    }
                }
            }
            Modello tmp = null;
            for (int i = 0; i < ApplicationData.modelli.size(); i++) {
                tmp = ApplicationData.modelli.get(i);
                if (tmp.getCodice_produzione().equals(cl.getModello_cod_prod()) &&
                        tmp.getMarca().equals(cl.getModello_marca())) {
                    break;
                }
            }
            viewHolder.marca.setText(tmp.getMarca());
            viewHolder.anno_modello.setText(tmp.getAnno());
            viewHolder.modello.setText(tmp.getNome());


            ArrayList<Lavoro> tmp1 = new ArrayList<>();
            for (int i = 0; i < ApplicationData.lavori.size(); i++) {
                Lavoro lv = ApplicationData.lavori.get(i);
                if (lv.getVeicolo().equals(cl.getNumero_telaio())) {
                    tmp1.add(lv);
                }
            }

            viewHolder.lavorazioni.setAdapter(new LavoriArrayAdapter(MainActivity.ctx, tmp1));
        }
        return view;
    }

    private class ViewHolder {
        public TextView targa;
        public TextView numero_telaio;
        public TextView proprietario;
        public TextView marca;
        public TextView modello;
        public TextView anno_modello;
        public ListView lavorazioni;
    }

}
