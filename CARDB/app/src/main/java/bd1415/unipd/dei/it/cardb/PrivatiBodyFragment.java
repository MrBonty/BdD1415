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

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class PrivatiBodyFragment extends Fragment {

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

        final View view = inflater.inflate(R.layout.clienti_body_fragment, container, false);

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
        viewHolder.pk = (TextView) view.findViewById(R.id.cliente_pk_data);
        viewHolder.telefono = (TextView) view.findViewById(R.id.cliente_telefono_data);
        viewHolder.citta = (TextView) view.findViewById(R.id.cliente_citta_data);
        viewHolder.provincia = (TextView) view.findViewById(R.id.cliente_provincia_data);
        viewHolder.indirizzo = (TextView) view.findViewById(R.id.cliente_indirizzo_data);
        viewHolder.veicoli = (ListView) view.findViewById(android.R.id.list);
        viewHolder.veicoli = (ListView) view.findViewById(android.R.id.list);
        viewHolder.numero_civico = (TextView) view.findViewById(R.id.cliente_civico_data);

        if (mIsVis) {
            final Privato cl = ApplicationData.privati.get(mPos);
            viewHolder.nome.setText(cl.getNome());
            viewHolder.cognomeLayout.setVisibility(View.VISIBLE);
            viewHolder.cognome.setText(cl.getCognome());

            viewHolder.pkTag.setText(R.string.cliente_cf_tag);
            viewHolder.pk.setText(cl.getCf());

            viewHolder.telefono.setText(cl.getTelefono());

            if (cl.getIndirizzo() != null) {
                viewHolder.indirizzo.setText(cl.getIndirizzo().indirizzo);
                viewHolder.indirizzo.setText(cl.getIndirizzo().numero_civico);
                viewHolder.citta.setText(cl.getIndirizzo().città);
                viewHolder.provincia.setText(cl.getIndirizzo().provincia);
            }
/*
                ArrayList<Veicolo> tmp = new ArrayList<>();
                for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                    Veicolo vl = ApplicationData.veicoli.get(i);
                    if (vl.getPrivato().equals(cl.getCf())) {
                        tmp.add(vl);
                    }
                }

                viewHolder.veicoli.setAdapter(new VeicoliArrayAdapter(MainActivity.ctx, tmp));
*/
            viewHolder.nome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.nome.getText().toString(),
                            false, MainActivity.ctx, viewHolder.nome).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        cl.setNome(viewHolder.nome.getText().toString(), true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.cognome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.cognome.getText().toString(),
                            false, MainActivity.ctx, viewHolder.cognome).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        cl.setCognome(viewHolder.cognome.getText().toString(), true);
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
                            true, MainActivity.ctx, viewHolder.pk).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        cl.setCf(viewHolder.pk.getText().toString(), true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
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
                                                        cl.setTelefono(viewHolder.telefono.getText().toString(), true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = cl.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.città = viewHolder.citta.getText().toString();
                                                        cl.setIndirizzo(n, true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = cl.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.indirizzo = viewHolder.indirizzo.getText().toString();
                                                        cl.setIndirizzo(n, true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = cl.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.provincia = viewHolder.provincia.getText().toString();
                                                        cl.setIndirizzo(n, true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = cl.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.numero_civico = viewHolder.numero_civico.getText().toString();
                                                        cl.setIndirizzo(n, true);
                                                        PrivatiMenuFragment.list.notifyDataSetChanged();
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
