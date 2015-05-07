package bd1415.unipd.dei.it.cardb;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import bd1415.unipd.dei.it.cardb.databasetables.Fattura;

public class FatturaDialog {

    private static Dialog mDialog;

    private FragmentManager mFM;

    public static final String POS = "position";
    public static final String ISVIS = "isVisible";

    public FatturaDialog(Fattura fattura) {
        mDialog = new Dialog(MainActivity.ctx);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.fattura_dialog);
        mDialog.setCanceledOnTouchOutside(true);

        int pos;
        if(fattura.getPagato() == 0){
            ApplicationData.isPayed = false;
            for (pos = 0; pos< ApplicationData.fattureNon.size(); pos++){
                if(fattura.getId() == ApplicationData.fattureNon.get(pos).getId()){
                    break;
                }
            }
        }else{
            ApplicationData.isPayed = true;
            for (pos = 0; pos< ApplicationData.fatturePagate.size(); pos++){
                if(fattura.getId() == ApplicationData.fatturePagate.get(pos).getId()){
                    break;
                }
            }
        }

        Fragment toView = new PagamentiBodyFragment();

        Bundle args = new Bundle();
        args.putInt(POS, pos);
        args.putBoolean(ISVIS, true);

        toView.setArguments(args);

        mDialog.show();

        mFM = MainActivity.act.getFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.lavorazioni_body, toView);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener){
        mDialog.setOnDismissListener(listener);
    }
}
