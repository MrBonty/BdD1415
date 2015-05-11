package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;

public class DescrizioniBodyFragment extends Fragment {

    private int mPos = -1;
    private boolean mIsVis = false;
    private ViewHolder viewHolder;
    private ImageView mImage;
    private LinearLayout mBody;

    private static final String[] TYPE = {"Guasto", "Manutenzione"};
    private static final ArrayList<String> TY = new ArrayList<>();

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

        TY.add(TYPE[0]);
        TY.add(TYPE[1]);

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(DescrizioniMenuFragment.POS);
            mIsVis = args.getBoolean(DescrizioniMenuFragment.ISVIS);
            mItem = ApplicationData.guastiManutenzioni.get(mPos);
        }

        viewHolder = new ViewHolder();

        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_descrizioni);
            mBody = (LinearLayout) view.findViewById(R.id.ll_descrizioni);
            mImage.setVisibility(View.GONE);
            mBody.setVisibility(View.VISIBLE);
        }else {
            mImage = (ImageView) view.findViewById(R.id.image_descrizioni);
            mBody = (LinearLayout) view.findViewById(R.id.ll_descrizioni);
            if(mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }

        viewHolder.id = (TextView) view.findViewById(R.id.descrizioni_id_data);
        viewHolder.tipo = (TextView) view.findViewById(R.id.descrizioni_tipo_data);
        viewHolder.descrizione = (TextView) view.findViewById(R.id.descrizioni_des_data);

        if (mIsVis) {

            String desc = mItem.substring(mItem.length() - 1);
            int id = Integer.parseInt(mItem.substring(0, mItem.length() - 1));
            mIsG = desc.equals(DescrizioniMenuFragment.GUASTO);
            if (mIsG) {
                for (int i = 0; i < ApplicationData.guasti.size(); i++) {
                    mG = ApplicationData.guasti.get(i);
                    if (mG.getId() == id) {
                        break;
                    }
                }
            } else {
                for (int i = 0; i < ApplicationData.manutenzioni.size(); i++) {
                    mM = ApplicationData.manutenzioni.get(i);
                    if (mM.getId() == id) {
                        break;
                    }
                }
            }

            if (mIsG) {
                viewHolder.id.setText(mG.getId() + "");
                viewHolder.tipo.setText(TYPE[0]);
                viewHolder.descrizione.setText(mG.getDescrizione());
            } else {
                viewHolder.id.setText(mM.getId() + "");
                viewHolder.tipo.setText(TYPE[1]);
                viewHolder.descrizione.setText(mM.getDescrizione());
            }

            viewHolder.descrizione.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.descrizione.getText().toString(),
                            false, MainActivity.ctx, viewHolder.descrizione).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if(mIsG){
                                mG.setDescrizione(viewHolder.descrizione.getText().toString(), true);
                            }else {
                                mM.setDescrizione(viewHolder.descrizione.getText().toString(), true);
                            }
                        }
                    });
                }
            });

            //TODO AGGIORNO TIPO??? meglio non farlo.... Bisogna aggiornare tutto!!
            /*
            viewHolder.tipo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.tipo.getText().toString(),
                            false, MainActivity.ctx, viewHolder.tipo,
                            new ArrayAdapter<String>(MainActivity.ctx, android.R.layout.simple_spinner_item, TY)).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface arg0) {

                            int i = Integer.parseInt(viewHolder.tipo.getText().toString());
                            if(i == 0){
                                if(mIsG){
                                    viewHolder.tipo.setText(TYPE[0]);
                                }else{
                                    viewHolder.tipo.setText(TYPE[0]);
                                    for(int j = 0; j < ApplicationData.manutenzioni.size(); j++){
                                        Manutenzione man = ApplicationData.manutenzioni.get(j);
                                        if(man.getId() == mM.getId()){
                                            mG = new Guasto(true);
                                            mG.setDescrizione(mM.getDescrizione(), true);
                                            mM = null;
                                            mIsG = true;

                                            ArrayList<String> guastiManutenzioni = new ArrayList<>();
                                            ArrayList<Integer> color = new ArrayList<>();
                                            for (int k = 0; k < ApplicationData.guasti.size(); k++) {
                                                Guasto g = ApplicationData.guasti.get(i);
                                                String tmp = g.getId() + DescrizioniMenuFragment.GUASTO;

                                                guastiManutenzioni.add(tmp);
                                                color.add(Color.parseColor("#66FF0000")); //Semitrasparent red
                                            }

                                            for (int k = 0; k < ApplicationData.manutenzioni.size(); k++) {
                                                Manutenzione m = ApplicationData.manutenzioni.get(i);
                                                String tmp = m.getId() + DescrizioniMenuFragment.MANUT;

                                                guastiManutenzioni.add(tmp);
                                                color.add(Color.parseColor("#66FFFF00")); //Semitrasparent yellow
                                            }
                                            Context con = DescrizioniMenuFragment.list.getContext();
                                            DescrizioniMenuFragment.list = new DescrizioniArrayAdapter(con, ApplicationData.guastiManutenzioni,color);
                                            DescrizioniMenuFragment.list.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }else{
                                if(mIsG) {
                                    viewHolder.tipo.setText(TYPE[1]);
                                    for(int j = 0; j < ApplicationData.guasti.size(); j++){
                                        Guasto gua = ApplicationData.guasti.get(j);
                                        if(gua.getId() == mG.getId()){
                                            mM = new Manutenzione(true);
                                            mM.setDescrizione(mG.getDescrizione(), true);
                                            mG = null;
                                            mIsG = false;

                                            ArrayList<String> guastiManutenzioni = new ArrayList<>();
                                            ArrayList<Integer> color = new ArrayList<>();
                                            for (int k = 0; k < ApplicationData.guasti.size(); k++) {
                                                Guasto g = ApplicationData.guasti.get(i);
                                                String tmp = g.getId() + DescrizioniMenuFragment.GUASTO;

                                                guastiManutenzioni.add(tmp);
                                                color.add(Color.parseColor("#66FF0000")); //Semitrasparent red
                                            }

                                            for (int k = 0; k < ApplicationData.manutenzioni.size(); k++) {
                                                Manutenzione m = ApplicationData.manutenzioni.get(i);
                                                String tmp = m.getId() + DescrizioniMenuFragment.MANUT;

                                                guastiManutenzioni.add(tmp);
                                                color.add(Color.parseColor("#66FFFF00")); //Semitrasparent yellow
                                            }

                                            Context con = DescrizioniMenuFragment.list.getContext();
                                            DescrizioniMenuFragment.list = new DescrizioniArrayAdapter(con, ApplicationData.guastiManutenzioni,color);
                                            DescrizioniMenuFragment.list.notifyDataSetChanged();
                                        }
                                    }
                                }else{
                                    viewHolder.tipo.setText(TYPE[1]);
                                }
                            }

                        }
                    });
                }
            });
            */
        }
        return view;
    }

    private class ViewHolder {
        public TextView id;
        public TextView tipo;
        public TextView descrizione;

    }

}
