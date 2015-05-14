package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;
import bd1415.unipd.dei.it.cardb.databasetables.Pezzo;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.R7;
import bd1415.unipd.dei.it.cardb.databasetables.R8;
import bd1415.unipd.dei.it.cardb.databasetables.Usato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

import static android.view.View.OnClickListener;

public class LavorazioniBodyFragment extends Fragment {

    private static Context mCtx = MainActivity.ctx;
    private static Dialog mTmpDial;
    private static Fattura mFat;
    private static Dialog mTmpDialogPicker;
    private static boolean toShowFattura = false;
    private int mPos = -1;
    private boolean mIsVis = false;
    private ViewHolder viewHolder;
    private ImageView mImage;
    private LinearLayout mBody;
    private boolean mIsFinished; // set a true if show finished work
    private Veicolo mVeicolo = null;
    private Lavoro mLavoro = null;
    private ArrayList<String> pezziGuastiManutenzioni;
    private ArrayAdapter<String> adapter;

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
        View view = inflater.inflate(R.layout.lavorazioni_body_fragment, container, false);

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(LavorazioniMenuFragment.POS);
            mIsVis = args.getBoolean(LavorazioniMenuFragment.ISVIS);
            mIsFinished = false;
        }

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_lavorazioni);
            mBody = (LinearLayout) view.findViewById(R.id.ll_lavorazioni);
            mImage.setVisibility(View.GONE);
            mBody.setVisibility(View.VISIBLE);
        } else {
            mImage = (ImageView) view.findViewById(R.id.image_lavorazioni);
            mBody = (LinearLayout) view.findViewById(R.id.ll_lavorazioni);
            if (mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }

        viewHolder.dataFine = (TextView) view.findViewById(R.id.lavoro_end_data);
        viewHolder.dataInizio = (TextView) view.findViewById(R.id.lavoro_start_data);
        viewHolder.fattura = (Button) view.findViewById(R.id.lavorazioni_fattura);
        viewHolder.guasto = (Button) view.findViewById(R.id.aggiungi_guasto);
        viewHolder.manutenzione = (Button) view.findViewById(R.id.aggiungi_manutenzione);
        viewHolder.pezzo = (Button) view.findViewById(R.id.aggiungi_pezzo);
        viewHolder.id = (TextView) view.findViewById(R.id.lavoro_id_data);
        viewHolder.veicoloTarga = (TextView) view.findViewById(R.id.lavoro_veicolo_targa_data);
        viewHolder.veicoloTelaio = (TextView) view.findViewById(R.id.lavoro_veicolo_telaio_data);
        viewHolder.lavori = (ListView) view.findViewById(android.R.id.list);

        if (mIsVis) {
            if (mIsFinished) {
                viewHolder.fattura.setText(R.string.lavoro_fattura_tag);
                mLavoro = ApplicationData.lavoriFiniti.get(mPos);
            } else {
                viewHolder.fattura.setText(R.string.lavoro_not_ended_tag);
                mLavoro = ApplicationData.lavoriInCorso.get(mPos);
            }
            findVeicolo();

            viewHolder.id.setText(mLavoro.getId() + "");

            viewHolder.dataInizio.setText(mLavoro.getData_inizio());
            if (mIsFinished) {
                viewHolder.dataFine.setText(mLavoro.getData_fine());
            } else {
                viewHolder.dataFine.setText(R.string.not_finished);
            }

            viewHolder.veicoloTelaio.setText(mVeicolo.getNumero_telaio());
            viewHolder.veicoloTarga.setText(mVeicolo.getTarga());

            viewHolder.fattura.setOnClickListener(getFatturaView());


            viewHolder.manutenzione.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //SpinnerDialogV2 sp = new SpinnerDialogV2.Builder(MainActivity.ctx, new ManutenzioniArrayAdapter(MainActivity.ctx, ApplicationData.manutenzioni), true));
                }
            });


            pezziGuastiManutenzioni = new ArrayList<>();
            final ArrayList<Integer> color = new ArrayList<>();
            for (int i = 0; i < ApplicationData.usato.size(); i++) {
                Usato u = ApplicationData.usato.get(i);
                if (mLavoro.getId() == u.getLavoro()) {
                    String tmp = "" + u.getPezzo();
                    for (int j = 0; j < ApplicationData.pezzi.size(); j++) {
                        Pezzo p = ApplicationData.pezzi.get(j);
                        if (p.getId() == u.getPezzo()) {
                            tmp += "P - " + p.getDescrizione().split(":")[0];
                            break;
                        }
                    }

                    pezziGuastiManutenzioni.add(tmp);
                    color.add(Color.parseColor("#6600FF00")); //Semitrasparent green
                }
            }


            for (int i = 0; i < ApplicationData.r7.size(); i++) {
                R7 r = ApplicationData.r7.get(i);
                if (mLavoro.getId() == r.getLavoro()) {
                    int guasto = r.getGuasto();
                    String tmp = "" + guasto;
                    for (int j = 0; j < ApplicationData.guasti.size(); j++) {
                        Guasto g = ApplicationData.guasti.get(j);
                        if (g.getId() == guasto) {
                            tmp += "G - " + g.getDescrizione().split(":")[0];
                            break;
                        }
                    }

                    pezziGuastiManutenzioni.add(tmp);
                    color.add(Color.parseColor("#66FF0000")); //Semitrasparent red
                }
            }
            for (int i = 0; i < ApplicationData.r8.size(); i++) {
                R8 r = ApplicationData.r8.get(i);
                if (mLavoro.getId() == r.getLavoro()) {
                    int manutenzione = r.getManutenzione();
                    String tmp = "" + manutenzione;
                    for (int j = 0; j < ApplicationData.manutenzioni.size(); j++) {
                        Manutenzione m = ApplicationData.manutenzioni.get(j);
                        if (m.getId() == manutenzione) {
                            tmp += "M - " + m.getDescrizione();
                            break;
                        }
                    }

                    pezziGuastiManutenzioni.add(tmp);
                    color.add(Color.parseColor("#66FFFF00")); //Semitrasparent yellow
                }
            }

            adapter = new DescrizioniArrayAdapter(mCtx, pezziGuastiManutenzioni, color);
            viewHolder.lavori.setAdapter(adapter);

            viewHolder.guasto.setOnClickListener(new OnClickListener() {
                String s = "";

                @Override
                public void onClick(View v) {
                    SpinnerDialogV2 dialog = new SpinnerDialogV2.Builder(s, MainActivity.ctx, new GuastiArrayAdapter(MainActivity.ctx, ApplicationData.guasti), true).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try {// s resta vuota
                                                            int pos = ApplicationData.positionLavoriDialogInsert;
                                                            Guasto man = ApplicationData.guasti.get(pos);
                                                            Toast.makeText(MainActivity.ctx, "" + pos + "  ", Toast.LENGTH_SHORT).show();
                                                            R7 r7 = new R7(mLavoro.getId(), man.getId(), true);
                                                            ApplicationData.r7.add(r7);
                                                            pezziGuastiManutenzioni.add(man.getId() + "G - " + man.getDescrizione());
                                                            color.add(Color.parseColor("#66FF0000"));  //red
                                                            ApplicationData.positionLavoriDialogInsert = -1;
                                                            adapter.notifyDataSetChanged();
                                                        } catch (Exception e) {

                                                        }
                                                    }
                                                }
                    );
                }
            });

            viewHolder.manutenzione.setOnClickListener(new OnClickListener() {
                String s = "";

                @Override
                public void onClick(View v) {
                    SpinnerDialogV2 dialog = new SpinnerDialogV2.Builder(s, MainActivity.ctx, new ManutenzioniArrayAdapter(MainActivity.ctx, ApplicationData.manutenzioni), true).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try { // s resta vuota
                                                            int pos = ApplicationData.positionLavoriDialogInsert;
                                                            Manutenzione man = ApplicationData.manutenzioni.get(pos);
                                                            Toast.makeText(MainActivity.ctx, "" + pos + "  ", Toast.LENGTH_SHORT).show();
                                                            R8 r8 = new R8(mLavoro.getId(), man.getId(), true);
                                                            ApplicationData.r8.add(r8);
                                                            pezziGuastiManutenzioni.add(man.getId() + "M - " + man.getDescrizione());
                                                            color.add(Color.parseColor("#66FFFF00")); //yellow
                                                            ApplicationData.positionLavoriDialogInsert = -1;
                                                            adapter.notifyDataSetChanged();

                                                        } catch (Exception e) {

                                                        }
                                                    }
                                                }
                    );
                }
            });

            viewHolder.pezzo.setOnClickListener(new OnClickListener() {
                String s = "";

                @Override
                public void onClick(View v) {
                    /*
                    SpinnerDialogV2 dialog = new SpinnerDialogV2.Builder(s, MainActivity.ctx, new MagazzinoArrayAdapter(MainActivity.ctx, ApplicationData.pezzi), true).build();
                    */
                    PickPezzoDialog dialog = new PickPezzoDialog(MainActivity.ctx);

                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try { //TODO SET NUMERO PEZZI USATI E UPDATE VALORE IN PEZZI
                                                            int pos = ApplicationData.positionLavoriDialogInsert;
                                                            Pezzo man = ApplicationData.pezzi.get(pos);
                                                            Toast.makeText(MainActivity.ctx, "" + pos + "  ", Toast.LENGTH_SHORT).show();
                                                            Usato usato = new Usato(mLavoro.getId(), man.getId(), true);
                                                            if (ApplicationData.quantità != -1) {
                                                                usato.setNumero_pezzi(ApplicationData.quantità, true);
                                                                man.setNumero_totale_pezzi(man.getNumero_totale_pezzi() - ApplicationData.quantità, true);

                                                                ApplicationData.quantità = -1;
                                                            }
                                                            ApplicationData.usato.add(usato);
                                                            color.add(Color.parseColor("#6600FF00")); //Semitrasparent green
                                                            pezziGuastiManutenzioni.add(man.getId() + "P - " + man.getDescrizione());
                                                            ApplicationData.positionLavoriDialogInsert = -1;
                                                            adapter.notifyDataSetChanged();

                                                        } catch (Exception e) {

                                                        }
                                                    }
                                                }
                    );
                }
            });

        }
        return view;
    }

    private OnClickListener getFatturaView() {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {

                Lavoro lavoro = mLavoro;

                mFat = null;
                int idFattura = lavoro.getFattura();

                if (!mIsFinished) {
                    String date = Util.getDate()[0];//TODO method for generate a date
                    mLavoro.setData_fine(date, true);
                    date = Util.getDate()[1];
                    mLavoro.setData_fine(date, false);

                    mTmpDial = new Dialog(mCtx);

                    mTmpDial.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    mTmpDial.setContentView(R.layout.tmp_dialog);

                    LinearLayout dialogLayout = (LinearLayout) mTmpDial.findViewById(R.id.dialog);
                    dialogLayout.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });

                    mTmpDial.setCanceledOnTouchOutside(false);


                    Button cancel = (Button) mTmpDial.findViewById(R.id.cancel);
                    Button addTo = (Button) mTmpDial.findViewById(R.id.add_to);
                    Button addNew = (Button) mTmpDial.findViewById(R.id.add_new);

                    cancel.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toShowFattura = false;
                            mTmpDial.dismiss();
                        }
                    });

                    addTo.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTmpDial.dismiss();
                            mTmpDialogPicker = new Dialog(mCtx);
                            mTmpDialogPicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            mTmpDialogPicker.setContentView(R.layout.picker_dialog);

                            LinearLayout dialogLayout = (LinearLayout) mTmpDialogPicker.findViewById(R.id.dialog);
                            dialogLayout.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                                            Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                }
                            });


                            List<String> forSpinner = new ArrayList<String>();
                            final ArrayList<Fattura> spinnerFat = new ArrayList<Fattura>();

                            for (int i = 0; i < ApplicationData.fattureNon.size(); i++) {
                                String tmp = "";
                                Fattura fatTmp = ApplicationData.fattureNon.get(i);

                                String token = "";
                                token += fatTmp.getId();
                                for (int j = token.length(); j < 5; j++) {
                                    token = " " + token;
                                }
                                tmp += token;
                                tmp += " - ";
                                if (fatTmp.getAzienda() != null) {
                                    tmp += fatTmp.getAzienda();
                                } else {
                                    tmp += fatTmp.getPrivato();
                                }
                                forSpinner.add(tmp);
                                spinnerFat.add(fatTmp);
                            }

                            Spinner spin = (Spinner) mTmpDialogPicker.findViewById(R.id.spinner_fatture);

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mCtx, android.R.layout.simple_spinner_item, forSpinner);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin.setAdapter(adapter);

                            Button cancel = (Button) mTmpDialogPicker.findViewById(R.id.cancel);
                            final Button add = (Button) mTmpDialogPicker.findViewById(R.id.add);
                            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    add.setEnabled(true);
                                    mFat = spinnerFat.get(position);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    add.setEnabled(false);
                                }
                            });

                            add.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    toShowFattura = true;
                                    mLavoro.setFattura(mFat.getId(), true);
                                    mTmpDialogPicker.dismiss();
                                }
                            });


                            cancel.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    toShowFattura = false;
                                    mTmpDialogPicker.dismiss();
                                }
                            });
                            mTmpDialogPicker.show();
                        }
                    });

                    addNew.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toShowFattura = true;
                            mFat = new Fattura(true);

                            mLavoro.setFattura(mFat.getId(), true);
                            mFat.setPagato(0, true);

                            if (mVeicolo == null) {

                                findVeicolo();
                            }


                            if (mVeicolo.getAzienda() != null) {
                                Azienda az = null;
                                for (int i = 0; i < ApplicationData.aziende.size(); i++) {
                                    az = ApplicationData.aziende.get(i);
                                    if (mVeicolo.getNumero_telaio().equals(mVeicolo.getAzienda())) {
                                        break;
                                    }
                                }
                                ApplicationData.fatture.add(mFat);
                                ApplicationData.fattureNon.add(mFat);
                                mFat.setAzienda(az.getPiva(), true);
                            } else {

                                Privato pr = null;
                                for (int i = 0; i < ApplicationData.privati.size(); i++) {
                                    pr = ApplicationData.privati.get(i);
                                    if (mVeicolo.getNumero_telaio().equals(mVeicolo.getPrivato())) {
                                        break;
                                    }
                                }

                                ApplicationData.fatture.add(mFat);
                                ApplicationData.fattureNon.add(mFat);
                                mFat.setAzienda(pr.getCf(), true);

                            }
                            mTmpDial.dismiss();
                        }
                    });

                    mTmpDial.show();

                } else {
                    for (int i = 0; i < ApplicationData.fatture.size(); i++) {
                        mFat = ApplicationData.fatture.get(i);
                        if (mFat.getId() == idFattura) {
                            toShowFattura = true;
                            break;
                        } else {
                            mFat = null;
                        }
                    }
                }

                if (toShowFattura) {
                    FatturaDialog dialog = new FatturaDialog(mFat);

                    toShowFattura = false;
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (!mIsFinished) {
                                ApplicationData.lavoriFiniti.add(mLavoro);
                                ApplicationData.lavoriInCorso.remove(mPos);
                                MainActivity.container.removeAllViewsInLayout();
                                ApplicationData.isPayed = false;
                                MainActivity.container.addView(MainActivity.lavorazioniLayout);
                                MainActivity.corrente = MainActivity.lavorazioniLayout;
                            }
                        }
                    });
                }
            }
        };
    }

    private void findVeicolo() {
        mVeicolo = null;
        for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
            mVeicolo = ApplicationData.veicoli.get(i);
            if (mVeicolo.getNumero_telaio().equals(mLavoro.getVeicolo())) {
                break;
            }
        }
    }

    private class ViewHolder {
        public TextView id;
        public TextView dataInizio;
        public TextView dataFine;
        public TextView veicoloTarga;
        public TextView veicoloTelaio;
        public Button fattura;
        public Button guasto;
        public Button manutenzione;
        public Button pezzo;
        public ListView lavori;
    }

}
