package bd1415.unipd.dei.it.cardb;

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

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class AziendeBodyFragment extends Fragment {

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
            mPos = args.getInt(AziendeMenuFragment.POS);
            mIsVis = args.getBoolean(AziendeMenuFragment.ISVIS);
        }

        final View view = inflater.inflate(R.layout.clienti_body_fragment, container, false);

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_clienti);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_clienti);
            mBody.setVisibility(View.VISIBLE);
        }else {
            mImage = (ImageView) view.findViewById(R.id.image_clienti);
            mBody = (LinearLayout) view.findViewById(R.id.ll_clienti);
            if(mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }
        viewHolder.nome = (TextView) view.findViewById(R.id.cliente_nome_data);
        viewHolder.cognomeLayout = (LinearLayout) view.findViewById(R.id.cliete_cognome_layout);
        viewHolder.cognome = (TextView) view.findViewById(R.id.cliente_cognome_data);
        viewHolder.pkTag = (TextView) view.findViewById(R.id.cliente_pk_tag);
        viewHolder.pk = (TextView) view.findViewById(R.id.cliente_pk_data);
        viewHolder.telefono = (TextView) view.findViewById(R.id.cliente_telefono_data);
        viewHolder.citta = (TextView) view.findViewById(R.id.cliente_citta_data);
        viewHolder.provincia = (TextView) view.findViewById(R.id.cliente_provincia_data);
        viewHolder.indirizzo = (TextView) view.findViewById(R.id.cliente_indirizzo_data);
        viewHolder.veicoli = (ListView) view.findViewById(android.R.id.list);
        viewHolder.numero_civico = (TextView) view.findViewById(R.id.cliente_civico_data);

        if (mIsVis) {
            final Azienda az = ApplicationData.aziende.get(mPos);

            viewHolder.nome.setText(az.getNome());
            viewHolder.cognomeLayout.setVisibility(View.GONE);

            viewHolder.pkTag.setText(R.string.cliente_piva_tag);
            viewHolder.pk.setText(az.getPiva());

            viewHolder.telefono.setText(az.getTelefono());

            if (az.getIndirizzo() != null) {
                viewHolder.indirizzo.setText(az.getIndirizzo().indirizzo);
                viewHolder.indirizzo.setText(az.getIndirizzo().numero_civico + "");
                viewHolder.citta.setText(az.getIndirizzo().città);
                viewHolder.provincia.setText(az.getIndirizzo().provincia);
            }

                ArrayList<Veicolo> tmp = new ArrayList<>();
                for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                    Veicolo vl = ApplicationData.veicoli.get(i);
                    if (vl.getAzienda().equals(az.getPiva())) {
                        tmp.add(vl);
                    }
                }

                viewHolder.veicoli.setAdapter(new VeicoliArrayAdapter(MainActivity.ctx, tmp));



          //TODO SISTEMARE
          viewHolder.nome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.nome.getText().toString(),
                            false, MainActivity.ctx, viewHolder.nome).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        az.setNome(viewHolder.nome.getText().toString(), true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.pk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.pk.getText().toString(),
                            false, MainActivity.ctx, viewHolder.pk).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        String newpiva = viewHolder.pk.getText().toString();
                                                        for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                                                            Veicolo tmp = ApplicationData.veicoli.get(i);
                                                            if (az.getPiva().equals(tmp.getAzienda())) {
                                                                ApplicationData.veicoli.get(i).setAzienda(newpiva, false);
                                                            }
                                                        }

                                                        az.setPiva(newpiva, true);
                                                        VeicoliMenuFragment.list.notifyDataSetChanged();

                                                    }
                                                }
                    );
                }
            });
            viewHolder.telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.telefono.getText().toString(),
                            false, MainActivity.ctx, viewHolder.telefono).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        az.setTelefono(viewHolder.telefono.getText().toString(), true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.citta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.citta.getText().toString(),
                            false, MainActivity.ctx, viewHolder.citta).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.città = viewHolder.citta.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.indirizzo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.indirizzo.getText().toString(),
                            false, MainActivity.ctx, viewHolder.indirizzo).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.indirizzo = viewHolder.indirizzo.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.provincia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.provincia.getText().toString(),
                            false, MainActivity.ctx, viewHolder.provincia).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.provincia = viewHolder.provincia.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.numero_civico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.numero_civico.getText().toString(),
                            false, MainActivity.ctx, viewHolder.numero_civico).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.numero_civico = viewHolder.numero_civico.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
        }
        return view;
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
        public TextView numero_civico;
    }


}
