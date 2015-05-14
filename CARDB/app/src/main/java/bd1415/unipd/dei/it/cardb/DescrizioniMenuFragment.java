package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;

public class DescrizioniMenuFragment extends ListFragment {
    public static final String POS = "position";
    public static final String ISVIS = "isVisible";
    public static final String GUASTO = "G";
    public static final String MANUT = "M";
    public static DescrizioniArrayAdapter list;
    private boolean isLarge = true;
    private FragmentManager mFM;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<String> guastiManutenzioni = new ArrayList<>();
        ArrayList<Integer> color = new ArrayList<>();
        for (int i = 0; i < ApplicationData.guasti.size(); i++) {
            Guasto g = ApplicationData.guasti.get(i);
            String tmp = "ID: " + g.getId() + "TIPO: " + GUASTO;

            guastiManutenzioni.add(tmp);
            color.add(Color.parseColor("#66FF0000")); //Semitrasparent red
        }

        for (int i = 0; i < ApplicationData.manutenzioni.size(); i++) {
            Manutenzione m = ApplicationData.manutenzioni.get(i);
            String tmp = "ID: " + m.getId() + "TIPO: " + MANUT;

            guastiManutenzioni.add(tmp);
            color.add(Color.parseColor("#66FFFF00")); //Semitrasparent yellow
        }

        ApplicationData.guastiManutenzioni = guastiManutenzioni;
        DescrizioniArrayAdapter tmp = new DescrizioniArrayAdapter(inflater.getContext(), ApplicationData.guastiManutenzioni, color);
        list = tmp;
        setListAdapter(tmp);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);

        if (isLarge) {

            Fragment toView = new DescrizioniBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, true);

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.descrizioni_body, toView);
            ft.addToBackStack(null);
            ft.commit();

            resizeFragment(this, (int) getResources().getDimension(R.dimen.small));
            isLarge = false;
            //Qui va il codice che avvia le modifiche sul secondo fragment.
        } else {
            resizeFragment(this, (int) getResources().getDimension(R.dimen.large));
            isLarge = true;
            Fragment toView = new DescrizioniBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, false);

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.descrizioni_body, toView);
            ft.addToBackStack(null);
            ft.commit();

        }
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
