package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
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
            mPos = args.getInt(VeicoliMenuFragment.POS);
            mIsVis = args.getBoolean(VeicoliMenuFragment.ISVIS);
        }

        View view = inflater.inflate(R.layout.veicoli_body_fragment, container, false);

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_veicoli);
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

        if (mIsVis) {

            final Veicolo veicolo = ApplicationData.veicoli.get(mPos);


            //TODO to fix, i set text non hanno alcun effetto
            viewHolder.targa.setText(veicolo.getTarga());
            viewHolder.numero_telaio.setText(veicolo.getNumero_telaio());

            viewHolder.proprietario.setText("edi");

            final boolean isPriv = true;
            /*
            if (veicolo.getAzienda() != null && !veicolo.getAzienda().equals("")) {
                isPriv = false;
                for (int i = 0; i < ApplicationData.aziende.size(); i++) {
                    Azienda tmp = ApplicationData.aziende.get(i);
                    if (tmp.getPiva().equals(veicolo.getAzienda())) {
                        viewHolder.proprietario.setText(tmp.getNome());
                        break;
                    }
                }
            } else {
                isPriv = true;
                for (int i = 0; i < ApplicationData.privati.size(); i++) {
                    Privato tmp = ApplicationData.privati.get(i);
                    if (tmp.getCf().equals(veicolo.getPrivato())) {
                        viewHolder.proprietario.setText(tmp.getNome() + " " + tmp.getCognome());
                        break;
                    }
                }
            }

            Modello tmp = null;
            for (int i = 0; i < ApplicationData.modelli.size(); i++) {
                tmp = ApplicationData.modelli.get(i);
                if (tmp.getCodice_produzione().equals(veicolo.getModello_cod_prod()) &&
                        tmp.getMarca().equals(veicolo.getModello_marca())) {
                    break;
                }
            }
            viewHolder.marca.setText(tmp.getMarca());
            viewHolder.anno_modello.setText(tmp.getAnno());
            viewHolder.modello.setText(tmp.getNome());


            ArrayList<Lavoro> tmp1 = new ArrayList<>();
            for (int i = 0; i < ApplicationData.lavori.size(); i++) {
                Lavoro lv = ApplicationData.lavori.get(i);
                if (lv.getVeicolo().equals(veicolo.getNumero_telaio())) {
                    tmp1.add(lv);
                }
            }

            viewHolder.lavorazioni.setAdapter(new LavoriArrayAdapter(MainActivity.ctx, tmp1));

*/
            viewHolder.numero_telaio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.numero_telaio.getText().toString(),
                            false, MainActivity.ctx, viewHolder.numero_telaio).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        veicolo.setNumero_telaio(viewHolder.numero_telaio.getText().toString(), true);
                                                        VeicoliMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.targa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.targa.getText().toString(),
                            false, MainActivity.ctx, viewHolder.targa).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        veicolo.setTarga(viewHolder.targa.getText().toString(), true);
                                                        VeicoliMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.marca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            viewHolder.modello.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.anno_modello.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.proprietario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPriv) {
                        SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.targa.getText().toString(),
                                false, MainActivity.ctx, viewHolder.targa, new PrivatiArrayAdapter(MainActivity.ctx, ApplicationData.privati)).build();
                        dialog.show();
                    } else {
                        SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.targa.getText().toString(),
                                false, MainActivity.ctx, viewHolder.targa, new AziendeArrayAdapter(MainActivity.ctx, ApplicationData.aziende)).build();
                        dialog.show();
                    }
                }
            });
            //TODO relink a scheda proprietario
            /*viewHolder.proprietario.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MainActivity.container.removeAllViewsInLayout();
                    if (isPriv) {
                        MainActivity.container.addView(MainActivity.privatiLayout);
                    } else {
                        MainActivity.container.addView(MainActivity.aziendeLayout);
                    }
                    return false;
                }
            });*/

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
