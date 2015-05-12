package bd1415.unipd.dei.it.cardb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;

public class ManutenzioniArrayAdapter extends ArrayAdapter<Manutenzione> {

    private Context context;
    private boolean useList = true;

    public ManutenzioniArrayAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }


    private class ViewHolder {
        TextView titleText;
        TextView infoText;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Manutenzione item = (Manutenzione) getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.guasto_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.guasto_name);
            holder.infoText = (TextView) viewToUse.findViewById(R.id.guasto_info);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        holder.titleText.setText(item.getId() + "");
        int i = 0;
        for (i = 0; i < item.getDescrizione().length(); i++) {
        }
        holder.infoText.setText(item.getDescrizione().substring(0, i));
        return viewToUse;
    }
}
