package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class FatturaDialog {

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";
    private static Dialog mDialog;

    //TODO SISTEMARE
    public FatturaDialog(Fattura fattura) {
        mDialog = new Dialog(MainActivity.ctx);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.fattura_dialog);
        mDialog.setCanceledOnTouchOutside(true);

        ViewHolder viewHolder = new ViewHolder();

        ImageView mImage = (ImageView) mDialog.findViewById(R.id.image_pagamenti);
        mImage.setVisibility(View.GONE);
        LinearLayout mBody = (LinearLayout) mDialog.findViewById(R.id.ll_pagamenti);
        mBody.setVisibility(View.VISIBLE);


        viewHolder.id = (TextView) mDialog.findViewById(R.id.pagamenti_id_data);
        viewHolder.data = (TextView) mDialog.findViewById(R.id.pagamenti_data_data);
        viewHolder.pagato = (CheckBox) mDialog.findViewById(R.id.pagamenti_pagato_ckbx);
        viewHolder.cliente = (TextView) mDialog.findViewById(R.id.pagamenti_cliente_data);
        viewHolder.lavori = (ListView) mDialog.findViewById(android.R.id.list);

        viewHolder.id.setText(fattura.getId() + "");
        if (fattura.getPagato() == 0) {
            viewHolder.pagato.setChecked(false);
            viewHolder.pagato.setEnabled(false);
        } else {
            viewHolder.pagato.setChecked(true);
            viewHolder.pagato.setEnabled(false);
        }

        Azienda az = null;
        Privato pr = null;
        if (fattura.getAzienda() != null || !("").equals(fattura.getAzienda())) {
            for (int i = 0; i < ApplicationData.aziende.size(); i++) {
                az = ApplicationData.aziende.get(i);
                if (az.getPiva().equals(fattura.getAzienda())) {
                    break;
                }
            }
            if (az != null) {
                viewHolder.cliente.setText(az.getNome());
            }
        } else {
            for (int i = 0; i < ApplicationData.privati.size(); i++) {
                pr = ApplicationData.privati.get(i);
                if (pr.getCf().equals(fattura.getPrivato())) {
                    break;
                }
            }
            if (pr != null) {
                viewHolder.cliente.setText(pr.getNome() + " " + pr.getCognome());
            }
        }

        ArrayList<Lavoro> item = new ArrayList<>();
        String data = "";
        for (int i = 0; i < ApplicationData.lavoriFiniti.size(); i++) {
            Lavoro tmp = ApplicationData.lavoriFiniti.get(i);
            if (tmp.getFattura() != fattura.getId()) {
                item.add(tmp);
            }

            if (Util.compareDate(data, tmp.getData_fine())) {
                data = tmp.getData_fine();
            }
        }
        viewHolder.data.setText(data);

        LavoriArrayAdapter adapter = new LavoriArrayAdapter(mDialog.getContext(), item);
        viewHolder.lavori.setAdapter(adapter);

        mDialog.show();
    }


    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
    }

    private class ViewHolder {
        public TextView id;
        public CheckBox pagato;
        public TextView data;
        public TextView cliente;
        public ListView lavori;
    }
}
