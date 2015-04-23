package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ClientiBodyFragment extends Fragment {

    private ViewHolder viewHolder;

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
        View view = inflater.inflate(R.layout.clienti_body_fragment, container, false);

        viewHolder = null;
        if (view == null) {
            viewHolder.nome = (TextView) view.findViewById(R.id.cliente_nome_data);
            viewHolder.cognomeLayout = (LinearLayout) view.findViewById(R.id.cliete_cognome_layout);
            viewHolder.cognome = (TextView) view.findViewById(R.id.cliente_cognome_data);
            viewHolder.pkTag = (TextView) view.findViewById(R.id.cliente_pk_tag);
            viewHolder.pk = (TextView) view.findViewById(R.id.cliente_nome_tag);
            viewHolder.telefono = (TextView) view.findViewById(R.id.cliente_telefono_data);
            viewHolder.citta = (TextView) view.findViewById(R.id.cliente_citta_data);
            viewHolder.provincia = (TextView) view.findViewById(R.id.cliente_provincia_data);
            viewHolder.indirizzo = (TextView) view.findViewById(R.id.cliente_indirizzo_data);
            viewHolder.veicoli = (ListView) view.findViewById(android.R.id.list);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }


    private class ViewHolder {
        public TextView nome;
        public LinearLayout cognomeLayout;
        public TextView cognome;
        public TextView pkTag;
        public TextView pk;
        public TextView telefono;
        public TextView citta;
        public TextView provincia;
        public TextView indirizzo;
        public ListView veicoli;
    }
}
