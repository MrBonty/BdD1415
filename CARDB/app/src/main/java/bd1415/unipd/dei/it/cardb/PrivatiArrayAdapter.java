package bd1415.unipd.dei.it.cardb;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class PrivatiArrayAdapter extends ArrayAdapter<Privato> {

    private Context context;
    private boolean useList = true;

    public PrivatiArrayAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Privato item = (Privato) getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.veicolo_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.veicolo_name);
            holder.infoText = (TextView) viewToUse.findViewById(R.id.veicolo_info);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        holder.titleText.setText(item.getNome() + " " + item.getCognome());
        holder.infoText.setText(item.getCf());
        return viewToUse;
    }

    private class ViewHolder {
        TextView titleText;
        TextView infoText;
    }
}
