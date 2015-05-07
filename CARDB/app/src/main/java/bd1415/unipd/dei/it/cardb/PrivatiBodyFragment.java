package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class PrivatiBodyFragment extends Fragment {

    private ViewHolder viewHolder = null;

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
  /*  @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(PrivatiMenuFragment.POS);
            mIsVis = args.getBoolean(PrivatiMenuFragment.ISVIS);
            mIsPrivate = args.getBoolean(PrivatiMenuFragment.ISP);
        }

        final Activity activity = getActivity();
        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) activity.findViewById(R.id.image_clienti);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) activity.findViewById(R.id.ll_clienti);
            mBody.setVisibility(View.VISIBLE);
        }
        viewHolder.nome = (TextView) activity.findViewById(R.id.cliente_nome_data);
        viewHolder.cognomeLayout = (LinearLayout) activity.findViewById(R.id.cliete_cognome_layout);
        viewHolder.cognome = (TextView) activity.findViewById(R.id.cliente_cognome_data);
        viewHolder.pkTag = (TextView) activity.findViewById(R.id.cliente_pk_tag);
        viewHolder.pk = (TextView) activity.findViewById(R.id.cliente_pk_data);
        viewHolder.telefono = (TextView) activity.findViewById(R.id.cliente_telefono_data);
        viewHolder.citta = (TextView) activity.findViewById(R.id.cliente_citta_data);
        viewHolder.provincia = (TextView) activity.findViewById(R.id.cliente_provincia_data);
        viewHolder.indirizzo = (TextView) activity.findViewById(R.id.cliente_indirizzo_data);
        viewHolder.veicoli = (ListView) activity.findViewById(android.R.id.list);
        viewHolder.numero_civico = (TextView) activity.findViewById(R.id.cliente_civico_data);



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
   /*         viewHolder.nome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.nome.setText(new DialogEdit(viewHolder.nome.getText().toString(),
                            false, MainActivity.ctx).get());
                    cl.setNome(viewHolder.nome.getText().toString(), true);
                }
            });
            viewHolder.cognome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.cognome.setText(new DialogEdit(viewHolder.cognome.getText().toString(),
                            false, MainActivity.ctx).get());
                    cl.setCognome(viewHolder.cognome.getText().toString(), true);
                }
            });
            viewHolder.pk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.pk.setText(new DialogEdit(viewHolder.pk.getText().toString(),
                            true, MainActivity.ctx).get());
                    cl.setCf(viewHolder.pk.getText().toString(), true);
                }
            });
            viewHolder.telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.telefono.setText(new DialogEdit(viewHolder.telefono.getText().toString(),
                            false, MainActivity.ctx).get());
                    cl.setTelefono(viewHolder.telefono.getText().toString(), true);
                }
            });
            viewHolder.citta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.citta.setText(new DialogEdit(viewHolder.citta.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.città = viewHolder.citta.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
            viewHolder.indirizzo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.indirizzo.setText(new DialogEdit(viewHolder.indirizzo.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.indirizzo = viewHolder.indirizzo.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
            viewHolder.provincia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.provincia.setText(new DialogEdit(viewHolder.provincia.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.provincia = viewHolder.provincia.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
            viewHolder.numero_civico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.numero_civico.setText(new DialogEdit(viewHolder.numero_civico.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.numero_civico = viewHolder.numero_civico.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
        }
    }
    */

    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(PrivatiMenuFragment.POS);
            mIsVis = args.getBoolean(PrivatiMenuFragment.ISVIS);
            mIsPrivate = args.getBoolean(PrivatiMenuFragment.ISP);
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
           /* viewHolder.cognome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.cognome.setText(new DialogEdit(viewHolder.cognome.getText().toString(),
                            false, MainActivity.ctx, viewHolder.cognome).get());
                }
            });
            cl.setCognome(viewHolder.cognome.getText().toString(), true);
            viewHolder.pk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.pk.setText(new DialogEdit(viewHolder.pk.getText().toString(),
                            true, MainActivity.ctx, viewHolder.pk).get());
                    cl.setCf(viewHolder.pk.getText().toString(), true);
                }
            });
            /*viewHolder.telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.telefono.setText(new DialogEdit(viewHolder.telefono.getText().toString(),
                            false, MainActivity.ctx).get());
                    cl.setTelefono(viewHolder.telefono.getText().toString(), true);
                }
            });
            viewHolder.citta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.citta.setText(new DialogEdit(viewHolder.citta.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.città = viewHolder.citta.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
            viewHolder.indirizzo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.indirizzo.setText(new DialogEdit(viewHolder.indirizzo.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.indirizzo = viewHolder.indirizzo.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
            viewHolder.provincia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.provincia.setText(new DialogEdit(viewHolder.provincia.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.provincia = viewHolder.provincia.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });
            viewHolder.numero_civico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.numero_civico.setText(new DialogEdit(viewHolder.numero_civico.getText().toString(),
                            false, MainActivity.ctx).get());
                    AddressType n = cl.getIndirizzo();
                    n.numero_civico = viewHolder.numero_civico.getText().toString();
                    cl.setIndirizzo(n, true);
                }
            });*/
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
