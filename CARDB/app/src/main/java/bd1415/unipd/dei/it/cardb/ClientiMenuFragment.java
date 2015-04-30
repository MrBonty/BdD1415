package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ClientiMenuFragment extends ListFragment {

    private boolean isLarge = true;
    private FragmentManager mFM;
    VeicoliBodyFragment mBodyFrag;

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";
    public static final String ISP = "isPrivate";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListAdapter(new ClientiArrayAdapter(inflater.getContext(), ApplicationData.privati));
        mFM = getFragmentManager();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);
        if (isLarge) {

            Fragment toView = ClientiBodyFragment.newIstance(true, true, pos);
            /*
            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, true);
            args.putBoolean(ISP, true); //is private

            toView.setArguments(args);*/

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.clienti_body, toView);
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
