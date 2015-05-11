package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Personale;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;


public class PersonaleArrayAdapter extends ArrayAdapter<Personale> {

    private Context context;

    public PersonaleArrayAdapter(Context context, List items) {
        super(context, R.layout.cliente_item, items);
        this.context = context;
    }


    private class ViewHolder {
        TextView titleText;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Personale item = (Personale) getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.cliente_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) viewToUse.findViewById(R.id.cliente_name);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        holder.titleText.setText(item.getCf()+ " " + item.getNome() + " " + item.getCognome() );
        return viewToUse;
    }
}
