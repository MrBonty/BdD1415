package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class VeicoliBodyFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.veicoli_body_fragment, container, false);

        viewHolder = null;
        if (view == null) {
            viewHolder.targa = (TextView) view.findViewById(R.id.veicolo_targa_data);
            viewHolder.numero_telaio = (TextView) view.findViewById(R.id.veicolo_telaio_data);
            viewHolder.proprietario = (TextView) view.findViewById(R.id.veicolo_proprietario_data);
            viewHolder.marca = (TextView) view.findViewById(R.id.veicolo_modello_marca_data);
            viewHolder.modello = (TextView) view.findViewById(R.id.veicolo_modello_nome_data);
            viewHolder.anno_modello = (TextView) view.findViewById(R.id.veicolo_modello_anno_data);
            viewHolder.lavorazioni = (ListView) view.findViewById(android.R.id.list);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        return view;
    }

    private class ViewHolder {
        public TextView targa;
        public TextView numero_telaio;
        public TextView proprietario;
        public TextView marca;
        public TextView modello;
        public TextView anno_modello;
        public ListView lavorazioni;
    }
}
