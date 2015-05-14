package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DescrizioniArrayAdapter extends ArrayAdapter<String> {

    private ArrayList<Integer> color;
    private Context mCtx;

    public DescrizioniArrayAdapter(Context context, List<String> items, ArrayList<Integer> color) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.color = color;
        mCtx = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        String tmp = (String) getItem(position);


        ViewHolder vh = null;

        View viewToUse = null;

        LayoutInflater mInflater = (LayoutInflater) mCtx
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.descrizione_item, null);

            vh = new ViewHolder();
            vh.titleText = (TextView) viewToUse.findViewById(R.id.descrizione_name);
            vh.titleInfo = (TextView) viewToUse.findViewById(R.id.descrizioni_info);
            viewToUse.setTag(vh);
        } else {
            viewToUse = convertView;
            vh = (ViewHolder) viewToUse.getTag();
        }

        viewToUse.setBackgroundColor(color.get(position));
        vh.titleText.setText(tmp);
        vh.titleInfo.setText("");

        return viewToUse;
    }

    private class ViewHolder {
        TextView titleText;
        TextView titleInfo;
    }
}

