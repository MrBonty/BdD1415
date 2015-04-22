package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class VeicoliMenuFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        setListAdapter(new VeicoliArrayAdapter(inflater.getContext(), MainActivity.veicoli));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
