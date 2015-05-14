package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Contiene;
import bd1415.unipd.dei.it.cardb.databasetables.Ordine;
import bd1415.unipd.dei.it.cardb.databasetables.Pezzo;

public class OrdiniBodyFragment extends Fragment {
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

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(OrdiniMenuFragment.POS);
            mIsVis = args.getBoolean(OrdiniMenuFragment.ISVIS);
        }

        final View view = inflater.inflate(R.layout.ordini_body_fragment, container, false);

        viewHolder = new ViewHolder();

        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_ordini);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_ordini);
            mBody.setVisibility(View.VISIBLE);
        } else {
            mImage = (ImageView) view.findViewById(R.id.image_ordini);
            mBody = (LinearLayout) view.findViewById(R.id.ll_ordini);
            if (mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }

        viewHolder.data = (TextView) view.findViewById(R.id.ordine_data_data);
        viewHolder.fornitore = (TextView) view.findViewById(R.id.ordine_fornitore_data);
        viewHolder.arrivato = (CheckBox) view.findViewById(R.id.ordine_arrivato_data);
        viewHolder.contiene = (ListView) view.findViewById(android.R.id.list);

        if (mIsVis) {
            final Ordine or = ApplicationData.ordini.get(mPos);

            viewHolder.data.setText(or.getData_or() + "");
            viewHolder.fornitore.setText(or.getFornitore() + "");

            if (or.getArrivato() == 0) {
                viewHolder.arrivato.setChecked(false);
                viewHolder.arrivato.setEnabled(true);
                viewHolder.arrivato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (viewHolder.arrivato.isChecked()) {
                            or.setArrivato(1, true);
                            ApplicationData.ordiniInCorso.remove(mPos);
                            for (int i = 0; i < ApplicationData.contiene.size(); i++) {
                                Contiene c = ApplicationData.contiene.get(i);
                                if (c.getOrdine_data().equals(or.getData_or()) && c.getOrdine_fornitore().equals(or.getFornitore())) {

                                    for (int j = 0; j < ApplicationData.pezzi.size(); j++) {
                                        Pezzo p = ApplicationData.pezzi.get(j);

                                        if (p.getId() == c.getPezzo()) {
                                            int numPezz = p.getNumero_totale_pezzi() + c.getNumero_pezzi();
                                            p.setNumero_totale_pezzi(numPezz, true);
                                            break;
                                        }
                                    }
                                }
                            }

                            viewHolder.arrivato.setEnabled(false);
                            OrdiniMenuFragment.list.notifyDataSetChanged();

                            view.setVisibility(View.GONE);
                        }
                    }

                });

            } else {
                viewHolder.arrivato.setChecked(true);
                viewHolder.arrivato.setEnabled(false);
            }


            ArrayList<Contiene> tmp = new ArrayList<Contiene>();
            for (int i = 0; i < ApplicationData.contiene.size(); i++) {
                Contiene c = ApplicationData.contiene.get(i);
                if (c.getOrdine_data().equals(or.getData_or()) && c.getOrdine_fornitore().equals(or.getFornitore())) {
                    tmp.add(c);
                }
            }

            viewHolder.contiene.setAdapter(new VeicoliArrayAdapter(MainActivity.ctx, tmp));
        }

        return view;
    }

    private class ViewHolder {
        public TextView data;
        public TextView fornitore;
        public CheckBox arrivato;
        public ListView contiene;
    }

}
