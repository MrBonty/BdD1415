package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.zip.Inflater;

import bd1415.unipd.dei.it.cardb.databasetables.Fattura;

public class PagamentiMenuFragment extends ListFragment {
    private boolean isLarge = true;
    private FragmentManager mFM;

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";

    public static PagamentiArrayAdapter list;
    public static LayoutInflater infl;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    //onActivityCreated
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        infl = inflater;
        PagamentiArrayAdapter adp = null;

        adp = new PagamentiArrayAdapter(inflater.getContext(), ApplicationData.fattureNon);

        list = adp;
        setListAdapter(adp);

        mFM = getFragmentManager();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);

        if (isLarge) {

            Fragment toView = new PagamentiBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, true);

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.pagamenti_body, toView);
            ft.addToBackStack(null);
            ft.commit();

            resizeFragment(this, (int) getResources().getDimension(R.dimen.small));
            isLarge = false;
            //Qui va il codice che avvia le modifiche sul secondo fragment.
        } else {
            resizeFragment(this, (int) getResources().getDimension(R.dimen.large));
            isLarge = true;
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

