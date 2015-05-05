package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

import static android.view.View.OnClickListener;

public class LavorazioniBodyFragment extends Fragment {

    private int mPos = -1;
    private boolean mIsVis = false;
    private boolean mIsPrivate = false;
    private Veicolo mVeicolo = null;
    private Lavoro mLavoro = null;
    private static Context mCtx  = MainActivity.ctx;
    private static Dialog mTmpDial;
    private static Fattura mFat;

    private static Dialog mTmpDialogPicker;

    private static boolean toShowFattura = false;

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
        return view;
    }

    private OnClickListener getFatturaView(final int position){
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                Lavoro lavoro = ApplicationData.lavori.get(position);
                mFat = null;
                int idFattura = lavoro.getId();

                if(lavoro.getData_fine() == null | lavoro.getData_fine() == ""){

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
                            for(int i = 0; i < ApplicationData.fatture.size(); i++){
                                String tmp = "";
                                Fattura fatTmp = ApplicationData.fatture.get(i);
                                if(fatTmp.getPagato() == 0) {
                                    String token = "";
                                    token += fatTmp.getId();
                                    for(int j= token.length(); j<5; j++){
                                        token = " " + token;

                                    }
                                    tmp += token;
                                    tmp += " - ";
                                    if(fatTmp.getAzienda() != null){
                                        tmp += fatTmp.getAzienda();
                                    }else {
                                        tmp += fatTmp.getPrivato();
                                    }
                                    forSpinner.add(tmp);
                                    spinnerFat.add(fatTmp);
                                }
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
                            if(mVeicolo == null){
                                findVeicolo();
                            }



                            if(mVeicolo.getAzienda() != null){
                                Azienda az = null;
                                for(int i = 0; i < ApplicationData.aziende.size(); i++){
                                    az = ApplicationData.aziende.get(i);
                                    if(mVeicolo.getNumero_telaio().equals(mVeicolo.getAzienda())){
                                        break;
                                    }
                                }
                                mFat.setAzienda(az.getPiva(),true);
                            }else{
                                Privato pr = null;
                                for(int i = 0; i < ApplicationData.privati.size(); i++){
                                    pr = ApplicationData.privati.get(i);
                                    if(mVeicolo.getNumero_telaio().equals(mVeicolo.getPrivato())){
                                        break;
                                    }
                                }
                                mFat.setAzienda(pr.getCf(),true);
                            }
                            mTmpDial.dismiss();
                        }
                    });

                    mTmpDial.show();

                } else{
                    for(int i = 0; i < ApplicationData.fatture.size(); i++){
                        mFat = ApplicationData.fatture.get(i);
                        if(mFat.getId() == idFattura){
                            toShowFattura = true;
                            break;
                        }else{
                            mFat = null;
                        }
                    }
                }

                if(toShowFattura) {
                    FatturaDialog dialog = new FatturaDialog(mFat);
                    toShowFattura = false;
                }
            }
        };
    }

    private void findVeicolo(){
        mVeicolo = null;
        for(int i = 0; i < ApplicationData.veicoli.size(); i++){
            mVeicolo = ApplicationData.veicoli.get(i);
            if(mVeicolo.getNumero_telaio().equals(mLavoro.getVeicolo())){
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
        public ListView lavori;
    }
}
