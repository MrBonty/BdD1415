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

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class PagamentiBodyFragment extends Fragment {


    private ViewHolder viewHolder = null;

    private int mPos = -1;
    private boolean mIsVis = false;
    private static boolean mIsPayed;

    private ImageView mImage;
    private LinearLayout mBody;

    private static Fattura mFattura;

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

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(VeicoliMenuFragment.POS);
            mIsVis = args.getBoolean(VeicoliMenuFragment.ISVIS);
            mIsPayed = ApplicationData.isPayed;
        }

        final View view = inflater.inflate(R.layout.pagamenti_body_fragment, container, false);
        viewHolder = new ViewHolder();

        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_pagamenti);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_pagamenti);
            mBody.setVisibility(View.VISIBLE);
        }

        viewHolder.id = (TextView) view.findViewById(R.id.pagamenti_id_data);
        viewHolder.data = (TextView) view.findViewById(R.id.pagamenti_data_data);
        viewHolder.pagato = (CheckBox) view.findViewById(R.id.pagamenti_pagato_ckbx);
        viewHolder.cliente = (TextView) view.findViewById(R.id.pagamenti_cliente_data);
        viewHolder.lavori = (ListView) view.findViewById(android.R.id.list);

        if(mIsVis) {
            mFattura = ApplicationData.fattureNon.get(mPos);
            viewHolder.id.setText(mFattura.getId()+"");
            viewHolder.pagato.setEnabled(true);
            viewHolder.pagato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(viewHolder.pagato.isChecked()){
                        mFattura.setPagato(1, false);
                        ApplicationData.fattureNon.remove(mPos);
                        ApplicationData.fatturePagate.add(mFattura);
                        for(int i = ApplicationData.fatturePagate.size()-1; i>= 0; i--) {

                            if(ApplicationData.fatturePagate.get(i).getId() == mFattura.getId()) {
                                mPos = i;
                                mIsPayed = true;
                                break;
                            }
                        }
                        viewHolder.pagato.setEnabled(false);
                        PagamentiMenuFragment.list.notifyDataSetChanged();
                        if(PagamentiFattiMenuFragment.list != null){
                            PagamentiFattiMenuFragment.list.notifyDataSetChanged();
                        }
                    }
                }
            });

            Azienda az= null;
            Privato pr= null;
            if(mFattura.getAzienda() != null || !mFattura.getAzienda().equals("")){
                for(int i = 0; i<ApplicationData.aziende.size(); i++){
                    az = ApplicationData.aziende.get(i);
                    if(az.getPiva().equals(mFattura.getAzienda())){
                        break;
                    }
                }
                if(az != null) {
                    viewHolder.cliente.setText(az.getNome());
                }
            }else{
                for(int i = 0; i<ApplicationData.privati.size(); i++){
                    pr = ApplicationData.privati.get(i);
                    if(pr.getCf().equals(mFattura.getPrivato())){
                        break;
                    }
                }
                if(pr != null) {
                    viewHolder.cliente.setText(pr.getNome() + " " + pr.getCognome());
                }
            }

            ArrayList<Lavoro> item = new ArrayList<>();
            String data = "";
            for(int i= 0; i< ApplicationData.lavoriFiniti.size(); i++){
                Lavoro tmp = ApplicationData.lavoriFiniti.get(i);
                if(tmp.getFattura() != mFattura.getId()){
                    item.add(tmp);
                }

                if(Util.compareDate(data, tmp.getData_fine())){
                    data = tmp.getData_fine();
                }
            }
            viewHolder.data.setText(data);

            LavoriArrayAdapter adapter = new LavoriArrayAdapter(getActivity().getBaseContext(), item);
            viewHolder.lavori.setAdapter(adapter);
        }
        return view;
    }

    private class ViewHolder{
        public TextView id;
        public CheckBox pagato;
        public TextView data;
        public TextView cliente;
        public ListView lavori;
    }

}
