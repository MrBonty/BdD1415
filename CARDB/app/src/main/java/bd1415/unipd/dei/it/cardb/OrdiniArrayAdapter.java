package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Ordine;

public class OrdiniArrayAdapter extends ArrayAdapter<Ordine> {
    private Context context;

    public OrdiniArrayAdapter(Context context, List items) {
        super(context, R.layout.ordini_item, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Ordine item = (Ordine) getItem(position);
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
        holder.titleText.setText("Data: " + item.getData_or() + " Fornitore: " + item.getFornitore());
        return viewToUse;
    }

    private class ViewHolder {
        TextView titleText;
    }
}

