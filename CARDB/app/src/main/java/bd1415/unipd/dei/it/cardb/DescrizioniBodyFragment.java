package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;

public class DescrizioniBodyFragment extends Fragment {

    private int mPos = -1;
    private boolean mIsVis = false;
    private ViewHolder viewHolder;
    private ImageView mImage;
    private LinearLayout mBody;

    private static final String[] TYPE = {"Guasto", "Manutenzione"};
    private String mItem = "";

    private boolean mIsG;
    private Guasto mG;
    private Manutenzione mM;

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
        View view = inflater.inflate(R.layout.descrizioni_body_fragment, container, false);

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(PrivatiMenuFragment.POS);
            mIsVis = args.getBoolean(PrivatiMenuFragment.ISVIS);
            mItem = ApplicationData.guastiManutenzioni.get(mPos);
        }

        viewHolder = new ViewHolder();

        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_lavorazioni);
            mBody = (LinearLayout) MainActivity.act.findViewById(R.id.ll_lavorazioni);
            mImage.setVisibility(View.GONE);
            mBody.setVisibility(View.VISIBLE);
        }

        viewHolder.id = (TextView) view.findViewById(R.id.descrizioni_id_data);
        viewHolder.tipo = (TextView) view.findViewById(R.id.descrizioni_tipo_data);
        viewHolder.descrizione = (TextView) view.findViewById(R.id.descrizioni_des_data);

        if (mIsVis) {

            String desc = mItem.substring(mItem.length()-1);
            int id = Integer.parseInt(mItem.substring(0,mItem.length()-1));
            mIsG = desc.equals(DescrizioniMenuFragment.GUASTO);
            if(mIsG){
                for(int i= 0; i<ApplicationData.guasti.size(); i++){
                    mG = ApplicationData.guasti.get(i);
                    if(mG.getId() == id){
                        break;
                    }
                }
            }else{
                for(int i= 0; i<ApplicationData.manutenzioni.size(); i++){
                    mM = ApplicationData.manutenzioni.get(i);
                    if(mM.getId() == id){
                        break;
                    }
                }
            }

            if (mIsG) {
                viewHolder.id.setText(mG.getId());
                viewHolder.tipo.setText(TYPE[0]);
                viewHolder.descrizione.setText(mG.getDescrizione());
            } else {
                viewHolder.id.setText(mM.getId());
                viewHolder.tipo.setText(TYPE[1]);
                viewHolder.descrizione.setText(mM.getDescrizione());
            }
        }
        return view;
    }

    private class ViewHolder {
        public TextView id;
        public TextView tipo;
        public TextView descrizione;

    }

}
