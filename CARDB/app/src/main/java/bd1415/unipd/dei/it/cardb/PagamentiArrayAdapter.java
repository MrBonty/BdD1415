package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;

public class PagamentiArrayAdapter extends ArrayAdapter<Fattura> {

    private Context context;

    public PagamentiArrayAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    private class ViewHolder {
        TextView titleText;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Fattura item = (Fattura) getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.pagamenti_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.pagameti_name);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText("Fattura n°" + item.getId());

        return viewToUse;
    }
}
