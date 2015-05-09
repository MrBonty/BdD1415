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

import bd1415.unipd.dei.it.cardb.databasetables.Pezzo;

public class MagazzinoBodyFragment extends Fragment {

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

    //onActivityCreated
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.magazzino_body_fragment, container, false);

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(MagazzinoMenuFragment.POS);
            mIsVis = args.getBoolean(MagazzinoMenuFragment.ISVIS);
        }


        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_magazzino);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_magazzino);
            mBody.setVisibility(View.VISIBLE);
        }else {
            mImage = (ImageView) view.findViewById(R.id.image_magazzino);
            mBody = (LinearLayout) view.findViewById(R.id.ll_magazzino);
            if(mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }

        viewHolder.id = (TextView) view.findViewById(R.id.magazzino_id_data);
        viewHolder.quantità = (TextView) view.findViewById(R.id.magazzino_num_data);
        viewHolder.prezzo_vendita = (TextView) view.findViewById(R.id.magazzino_prezzo_data);
        viewHolder.descrizione = (TextView) view.findViewById(R.id.magazzino_desc_data);

        if(mIsVis){
            Pezzo pezzo = ApplicationData.pezzi.get(mPos);

            viewHolder.id.setText(pezzo.getId()+"");
            viewHolder.quantità.setText(pezzo.getNumero_totale_pezzi()+ "");
            viewHolder.prezzo_vendita.setText(pezzo.getPrezzo_vendita()+ "");
            viewHolder.descrizione.setText(pezzo.getDescrizione());
        }
        return view;
    }

    private class ViewHolder {
        public TextView id;
        public TextView descrizione;
        public TextView quantità;
        public TextView prezzo_vendita;

    }

}

