package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Modello;


public class ModelloArrayAdapter extends ArrayAdapter<Modello> {

    private Context context;
    private boolean useList = true;

    public ModelloArrayAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }


    private class ViewHolder {
        TextView titleText;
        TextView codiceText;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Modello item = (Modello) getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.modello_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.modello_name);
            holder.codiceText = (TextView) viewToUse.findViewById(R.id.modello_codice);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText(item.getMarca() + " " + item.getNome());
        holder.codiceText.setText(item.getCodice_produzione());

        return viewToUse;
    }

}
