package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class LavorazioniBodyFragment extends Fragment {

    private int mPos = -1;
    private boolean mIsVis = false;
    private boolean mIsPrivate = false;
    private Veicolo mVeicolo = null;
    private Lavoro mLavoro = null;

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

    private View.OnClickListener getFatturaView(final int position){
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Lavoro lavoro = ApplicationData.lavori.get(position);
                Fattura fat = null;
                int idFattura = lavoro.getId();
                if(idFattura == 0){
                    fat = new Fattura(true);
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
                        fat.setAzienda(az.getPiva(),true);
                    }else{
                        Privato pr = null;
                        for(int i = 0; i < ApplicationData.privati.size(); i++){
                            pr = ApplicationData.privati.get(i);
                            if(mVeicolo.getNumero_telaio().equals(mVeicolo.getPrivato())){
                                break;
                            }
                        }
                        fat.setAzienda(pr.getCf(),true);
                    }
                }else{
                    for(int i = 0; i < ApplicationData.fatture.size(); i++){
                        fat = ApplicationData.fatture.get(i);
                        if(fat.getId() == idFattura){
                            break;
                        }else{
                            fat= null;
                        }
                    }
                }

                FatturaDialog dialog = new FatturaDialog(fat);
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
