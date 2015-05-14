package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Contiene;
import bd1415.unipd.dei.it.cardb.databasetables.Pezzo;

public class OrdiniContieneArrayAdapter extends ArrayAdapter<Contiene> {
    private Context context;

    public OrdiniContieneArrayAdapter(Context context, List items) {
        super(context, R.layout.ordini_item, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Contiene item = (Contiene) getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.ordini_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.ordine_name);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        String tmp = "";
        for (int i = 0; i < ApplicationData.pezzi.size(); i++) {
            Pezzo p = ApplicationData.pezzi.get(i);
            if (item.getPezzo() != p.getId()) {
                tmp += p.getId() + "P " + (p.getDescrizione().split(":"))[0];
                tmp += " N°" + item.getNumero_pezzi() + " Prezzo: " + item.getPrezzo_pezzo();
                break;
            }
        }

        holder.titleText.setText(tmp);
        return viewToUse;
    }

    private class ViewHolder {
        TextView titleText;
    }
}
