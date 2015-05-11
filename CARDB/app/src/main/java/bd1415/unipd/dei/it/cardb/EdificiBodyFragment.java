package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Personale;

public class EdificiBodyFragment extends Fragment{

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
            mPos = args.getInt(EdificiMenuFragment.POS);
            mIsVis = args.getBoolean(EdificiMenuFragment.ISVIS);
        }

        final View view = inflater.inflate(R.layout.edifici_body_fragment, container, false);

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_edifici);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_edifici);
            mBody.setVisibility(View.VISIBLE);
        }else {
            mImage = (ImageView) view.findViewById(R.id.image_edifici);
            mBody = (LinearLayout) view.findViewById(R.id.ll_edifici);
            if(mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }

        viewHolder.id = (TextView) view.findViewById(R.id.edificio_id_data);
        viewHolder.tipologia = (TextView) view.findViewById(R.id.edificio_tip_data);
        viewHolder.respId = (TextView) view.findViewById(R.id.edificio_resp_id_data);
        viewHolder.respNom = (TextView) view.findViewById(R.id.edificio_resp_data);
        viewHolder.citta = (TextView) view.findViewById(R.id.edificio_citta_data);
        viewHolder.indirizzo = (TextView) view.findViewById(R.id.edificio_indirizzo_data);
        viewHolder.provincia = (TextView) view.findViewById(R.id.edificio_provincia_data);
        viewHolder.numero_civico = (TextView) view.findViewById(R.id.edificio_civico_data);
        viewHolder.personale = (ListView) view.findViewById(android.R.id.list);

        if(mIsVis){
            final Edificio ed = ApplicationData.edifici.get(mPos);

            viewHolder.id.setText(ed.getId() + "");
            viewHolder.tipologia.setText(ed.getTipologia());

            if(ed.getIndirizzo() != null){
                viewHolder.citta.setText(ed.getIndirizzo().città);
                viewHolder.indirizzo.setText(ed.getIndirizzo().indirizzo);
                viewHolder.provincia.setText(ed.getIndirizzo().provincia);
                viewHolder.numero_civico.setText(ed.getIndirizzo().numero_civico + "");
            }

            ArrayList<Personale> item = new ArrayList<>();
            for(int i = 0; i< ApplicationData.personale.size(); i++){
                Personale tmp = ApplicationData.personale.get(i);
                if(tmp.getEdificio() == ed.getId()){
                    if(tmp.getResponsabile() == 0){
                        item.add(tmp);
                    }else{
                        viewHolder.respId.setText(tmp.getCf());
                        viewHolder.respNom.setText(tmp.getNome() + " " + tmp.getCognome());
                    }
                }
            }

            viewHolder.personale.setAdapter(new PersonaleArrayAdapter(getActivity().getBaseContext(), item));
        }
        return view;
    }

    private class ViewHolder {
        public TextView id;
        public TextView tipologia;
        public TextView respId;
        public TextView respNom;
        public TextView citta;
        public TextView provincia;
        public TextView indirizzo;
        public TextView numero_civico;
        public ListView personale;
    }

}
