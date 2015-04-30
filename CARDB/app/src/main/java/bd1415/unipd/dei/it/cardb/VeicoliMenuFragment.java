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

public class VeicoliMenuFragment extends ListFragment {

    private boolean isLarge = true;
    private FragmentManager mFM;
    private ImageView mImage;
    private LinearLayout mBody;
    VeicoliBodyFragment mBodyFrag;

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListAdapter(new VeicoliArrayAdapter(inflater.getContext(), ApplicationData.veicoli));

        mImage = (ImageView) MainActivity.act.findViewById(R.id.image_veicoli);
        mBody = (LinearLayout) MainActivity.act.findViewById(R.id.ll_veicoli);

        mFM = getFragmentManager();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView list, View v, int pos, long id) {
        super.onListItemClick(list, v, pos, id);
        if (isLarge) {
            mImage.setVisibility(View.GONE);
            mBody.setVisibility(View.VISIBLE);

            Fragment toView = new VeicoliBodyFragment();

            Bundle args = new Bundle();
            args.putInt(POS, pos);
            args.putBoolean(ISVIS, true);

            toView.setArguments(args);

            mFM = MainActivity.act.getFragmentManager();
            FragmentTransaction ft = mFM.beginTransaction();
            ft.replace(R.id.veicoli_body, toView);
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
