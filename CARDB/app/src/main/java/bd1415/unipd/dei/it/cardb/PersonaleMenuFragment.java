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

import bd1415.unipd.dei.it.cardb.databasetables.Personale;

public class PersonaleMenuFragment extends ListFragment {

    private boolean isLarge = true;
    private FragmentManager mFM;

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";

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

    public static PersonaleArrayAdapter list = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PersonaleArrayAdapter prv = new PersonaleArrayAdapter(inflater.getContext(), ApplicationData.personale);
        setListAdapter(prv);
        list = prv;
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);
        if (isLarge) {

            Fragment toView = new PersonaleBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, true);

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.personale_body, toView);
            ft.addToBackStack(null);
            ft.commit();

            resizeFragment(this, (int) getResources().getDimension(R.dimen.small));
            isLarge = false;
        } else {
            resizeFragment(this, (int) getResources().getDimension(R.dimen.large));
            isLarge = true;
            Fragment toView = new PersonaleBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, false);

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.personale_body, toView);
            ft.addToBackStack(null);
            ft.commit();
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
