package bd1415.unipd.dei.it.cardb;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InterrogazioniBodyFragment extends Fragment {

    private static Context mCtx = MainActivity.ctx;
    private int mPos = -1;

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
        View view = inflater.inflate(R.layout.interrogazioni_body_fragment, container, false);

        final Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(PrivatiMenuFragment.POS);
        }
        int month = 12;
        switch (month) {
            case 1:
                ;
                break;
            case 2:
                ;
                break;
            case 3:
                ;
                break;
            case 4:
                ;
                break;
            case 5:
                ;
                break;
            case 6:
                ;
                break;
            case 7:
                ;
                break;
            case 8:
                ;
                break;
            case 9:
                ;
                break;
            case 10:
                ;
                break;
            case 11:
                ;
                break;
            case 12:
                ;
                break;
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.risultato = (TextView) view.findViewById(R.id.risultati);
        viewHolder.attributi = (TextView) view.findViewById(R.id.attributi);

        return view;
    }

    private class ViewHolder {
        public TextView attributi;
        public TextView risultato;
    }

}
