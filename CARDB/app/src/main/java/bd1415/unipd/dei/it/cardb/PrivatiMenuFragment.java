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

public class PrivatiMenuFragment extends ListFragment {

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";
    public static final String ISP = "isPrivate";
    public static PrivatiArrayAdapter list = null;
    VeicoliBodyFragment mBodyFrag;
    private boolean isLarge = true;
    private FragmentManager mFM;

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
        PrivatiArrayAdapter prv = new PrivatiArrayAdapter(inflater.getContext(), ApplicationData.privati);
        setListAdapter(prv);
        list = prv;
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);
        if (isLarge) {

            Fragment toView = new PrivatiBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, true);
            args.putBoolean(ISP, true); //is private

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.privati_body, toView);
            ft.addToBackStack(null);
            ft.commit();

            resizeFragment(this, (int) getResources().getDimension(R.dimen.small));
            isLarge = false;
            //Qui va il codice che avvia le modifiche sul secondo fragment.
        } else {
            resizeFragment(this, (int) getResources().getDimension(R.dimen.large));
            isLarge = true;
            Fragment toView = new PrivatiBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, false);
            args.putBoolean(ISP, true); //is private

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.privati_body, toView);
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
