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
import bd1415.unipd.dei.it.cardb.databasetables.Contiene;
import bd1415.unipd.dei.it.cardb.databasetables.Fornitore;
import bd1415.unipd.dei.it.cardb.databasetables.Ordine;

public class FornitoriBodyFragment extends Fragment {

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
            mPos = args.getInt(FornitoriMenuFragment.POS);
            mIsVis = args.getBoolean(FornitoriMenuFragment.ISVIS);
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
        viewHolder.iban_tag = (TextView) view.findViewById(R.id.cliente_cognome_tag);
        viewHolder.iban = (TextView) view.findViewById(R.id.cliente_cognome_data);
        viewHolder.pkTag = (TextView) view.findViewById(R.id.cliente_pk_tag);
        viewHolder.pk = (TextView) view.findViewById(R.id.cliente_pk_data);
        viewHolder.telefono = (TextView) view.findViewById(R.id.cliente_telefono_data);
        viewHolder.citta = (TextView) view.findViewById(R.id.cliente_citta_data);
        viewHolder.provincia = (TextView) view.findViewById(R.id.cliente_provincia_data);
        viewHolder.indirizzo = (TextView) view.findViewById(R.id.cliente_indirizzo_data);
        viewHolder.numero_civico = (TextView) view.findViewById(R.id.cliente_civico_data);
        viewHolder.ordini = (ListView) view.findViewById(android.R.id.list);

        if (mIsVis) {
            final Fornitore az = ApplicationData.fornitori.get(mPos);

            viewHolder.nome.setText(az.getNome());
            viewHolder.cognomeLayout.setVisibility(View.VISIBLE);
            viewHolder.iban_tag.setText("Iban: ");
            viewHolder.iban.setText(az.getIban());
            viewHolder.pkTag.setText(R.string.cliente_piva_tag);
            viewHolder.pk.setText(az.getPiva());

            viewHolder.telefono.setText(az.getTelefono());

            if (az.getIndirizzo() != null) {
                viewHolder.indirizzo.setText(az.getIndirizzo().indirizzo);
                viewHolder.indirizzo.setText(az.getIndirizzo().numero_civico+ "");
                viewHolder.citta.setText(az.getIndirizzo().città);
                viewHolder.provincia.setText(az.getIndirizzo().provincia);
            }

            ArrayList<Ordine> ordini = new ArrayList<>();
            for(int i = 0; i< ApplicationData.ordini.size(); i++){
                Ordine tmpOr = ApplicationData.ordini.get(i);
                if(tmpOr.getFornitore().equals(az.getPiva())){
                    ordini.add(tmpOr);
                }
            }
            viewHolder.ordini.setAdapter(new OrdiniArrayAdapter(MainActivity.ctx, ordini));

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
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
                                                        for(int i = 0; i< ApplicationData.ordini.size(); i++){
                                                            Ordine tmp = ApplicationData.ordini.get(i);
                                                            if (tmp.getFornitore().equals(az.getPiva())) {
                                                                ApplicationData.ordini.get(i).setFornitore(newpiva, false);
                                                            }
                                                        }
                                                        for(int i = 0; i< ApplicationData.contiene.size(); i++){
                                                            Contiene tmp = ApplicationData.contiene.get(i);
                                                            if (tmp.getOrdine_fornitore().equals(az.getPiva())) {
                                                                ApplicationData.contiene.get(i).setOrdine_fornitore(newpiva, false);
                                                            }

                                                        }
                                                        az.setPiva(newpiva, true);
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.iban.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.iban.getText().toString(),
                            false, MainActivity.ctx, viewHolder.iban).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        az.setIban(viewHolder.iban.getText().toString(), true);
                                                        FornitoriMenuFragment.list.notifyDataSetChanged();
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
        public TextView iban_tag;
        public TextView iban;
        public TextView pkTag;
        public TextView pk;
        public TextView telefono;
        public TextView citta;
        public TextView provincia;
        public TextView indirizzo;
        public ListView ordini;
        public TextView numero_civico;
    }


}
