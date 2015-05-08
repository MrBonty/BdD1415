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

import bd1415.unipd.dei.it.cardb.databasetables.Fattura;

public class PagamentiFattiBodyFragment extends Fragment {

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
             mFattura = ApplicationData.fatturePagate.get(mPos);
             viewHolder.pagato.setChecked(true);
             viewHolder.pagato.setEnabled(false);

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
