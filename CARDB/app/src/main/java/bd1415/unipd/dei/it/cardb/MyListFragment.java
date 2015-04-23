package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import bd1415.unipd.dei.it.cardb.databasetables.Privato;

/**
 * Created by enrico on 23/04/15.
 */
public class MyListFragment extends ListFragment {

    private boolean isLarge = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (ApplicationData.posizioneCorrente == 0) {
            setListAdapter(new ClientiArrayAdapter(inflater.getContext(), ApplicationData.clienti));
        } else if (ApplicationData.posizioneCorrente == 1) {
            setListAdapter(new VeicoliArrayAdapter(inflater.getContext(), ApplicationData.veicoli));
        }
        /*switch (ApplicationData.posizioneCorrente) {
            case 0:

                break;
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }*/
        //setListAdapter(new VeicoliArrayAdapter(inflater.getContext(), ApplicationData.veicoli));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);
        if (isLarge) {
            resizeFragment(this, 2500);
            //Qui va il codice che avvia le modifiche sul secondo fragment.
        } else {
            resizeFragment(this, 400);
        }

        Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();
    }


    private void resizeFragment(Fragment f, int newWidth) {
        if (f != null) {
            View view = f.getView();
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(newWidth, getView().getHeight());
            view.setLayoutParams(p);
            view.requestLayout();
        }
    }


}
