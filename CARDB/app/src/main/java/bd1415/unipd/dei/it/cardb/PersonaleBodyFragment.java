package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Lavora_a;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Modello;
import bd1415.unipd.dei.it.cardb.databasetables.Personale;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class PersonaleBodyFragment extends Fragment{

    private ViewHolder viewHolder = null;

    private int mPos = -1;
    private boolean mIsVis = false;
    private ImageView mImage;
    private LinearLayout mBody;

    private LavoraAArrayAdapter mAdapter;

    private Personale mCurrentPer;

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
        viewHolder.cf = (TextView) view.findViewById(R.id.personale_cf_data);
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
        viewHolder.add_lav = (Button) view.findViewById(R.id.personale_lavoro_button);

        if (mIsVis) {
            final Personale per = ApplicationData.personale.get(mPos);
            mCurrentPer = per;

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
                                newW.setText("Nuovo Resp = " + per.getCognome() + " " + per.getNome() + " " + per.getCf());

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

            mAdapter = new LavoraAArrayAdapter(MainActivity.ctx, item);
            viewHolder.lavori.setAdapter(mAdapter);

            viewHolder.add_lav.setOnClickListener(openDialog());

            viewHolder.cf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.cf.getText().toString(),
                            true, MainActivity.ctx, viewHolder.cf).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        per.setCf(viewHolder.cf.getText().toString(), true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.nome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.nome.getText().toString(),
                            false, MainActivity.ctx, viewHolder.nome).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        per.setNome(viewHolder.nome.getText().toString(), true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        per.setCognome(viewHolder.cognome.getText().toString(), true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        per.setIban(viewHolder.iban.getText().toString(), true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );

                }
            });

            viewHolder.contratto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.contratto.getText().toString(),
                            false, MainActivity.ctx, viewHolder.contratto).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        per.setContratto(viewHolder.contratto.getText().toString(), true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        per.setTelefono(viewHolder.telefono.getText().toString(), true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = per.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.città = viewHolder.citta.getText().toString();
                                                        per.setIndirizzo(n, true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = per.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.indirizzo = viewHolder.indirizzo.getText().toString();
                                                        per.setIndirizzo(n, true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = per.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.provincia = viewHolder.provincia.getText().toString();
                                                        per.setIndirizzo(n, true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
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
                                                        AddressType n = per.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.numero_civico = viewHolder.numero_civico.getText().toString();
                                                        per.setIndirizzo(n, true);
                                                        PersonaleMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.edificio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.edificio.getText().toString(),
                            true, MainActivity.ctx, viewHolder.edificio, new EdificiArrayAdapter(MainActivity.ctx, ApplicationData.edifici), true).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try {
                                                            Edificio az = ApplicationData.edifici.get(Integer.parseInt(viewHolder.edificio.getText().toString()));
                                                            viewHolder.edificio.setText(az.getId() + " " + az.getTipologia());
                                                            per.setEdificio(az.getId(), true);
                                                        } catch (java.lang.NumberFormatException ex) {

                                                        }
                                                    }
                                                }
                    );
                }
            });


        }
        return view;
    }

    private View.OnClickListener openDialog(){
        return new View.OnClickListener() {

            private Lavoro selWork;
            private Dialog mTmpDialogPicker;

            @Override
            public void onClick(View v) {


                Context mCtx = MainActivity.ctx;

                mTmpDialogPicker = new Dialog(mCtx);
                mTmpDialogPicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mTmpDialogPicker.setContentView(R.layout.picker_dialog);

                LinearLayout dialogLayout = (LinearLayout) mTmpDialogPicker.findViewById(R.id.dialog);
                dialogLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                });


                List<String> forSpinner = new ArrayList<String>();
                final ArrayList<Lavoro> spinnerFat = new ArrayList<Lavoro>();

                for (int i = 0; i < ApplicationData.lavoriInCorso.size(); i++) {
                    String tmp = "";
                    Lavoro lavTmp = ApplicationData.lavoriInCorso.get(i);
                    boolean toInsert = true;

                    for(int j = 0; j < ApplicationData.lavora_a.size(); j++){
                        Lavora_a lavA = ApplicationData.lavora_a.get(j);
                        if(lavA.getLavoro() == lavTmp.getId() && mCurrentPer.getCf().equals(lavA.getPersonale())){
                            toInsert = false;
                            break;
                        }
                    }

                    if(toInsert) {
                        String token = "";
                        token += lavTmp.getId();
                        for (int j = token.length(); j < 5; j++) {
                            token = " " + token;
                        }
                        tmp += token;
                        tmp += " - ";
                        tmp += lavTmp.getData_inizio();

                        forSpinner.add(tmp);
                        spinnerFat.add(lavTmp);
                    }
                }

                Spinner spin = (Spinner) mTmpDialogPicker.findViewById(R.id.spinner_fatture);
                TextView title = (TextView) mTmpDialogPicker.findViewById(R.id.title);

                title.setText("Selezionare Lavoro:");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mCtx, android.R.layout.simple_spinner_item, forSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapter);

                Button cancel = (Button) mTmpDialogPicker.findViewById(R.id.cancel);
                final Button add = (Button) mTmpDialogPicker.findViewById(R.id.add);
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        add.setEnabled(true);
                        selWork = spinnerFat.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        add.setEnabled(false);
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addWorkToListView(selWork);

                        mTmpDialogPicker.dismiss();
                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTmpDialogPicker.dismiss();
                    }
                });
                mTmpDialogPicker.show();
            }
        };
    }

    private void addWorkToListView(Lavoro lavToAdd){
        String ins = "";

        ins = lavToAdd.getId() + " " + lavToAdd.getData_inizio() + " ore lav: ";
        ins += 0 + " straordinari: " + 0;

        Lavora_a tmp = new Lavora_a(mCurrentPer.getCf(),lavToAdd.getId(),true);

        mAdapter.add(ins);
        mAdapter.notifyDataSetChanged();
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
        public Button add_lav;
    }

}
