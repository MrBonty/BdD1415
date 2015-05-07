package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.view.Window;

import bd1415.unipd.dei.it.cardb.databasetables.Fattura;

public class FatturaDialog {

    private static Dialog mDialog;

    //TODO
    public FatturaDialog(Fattura fattura) {
        mDialog = new Dialog(MainActivity.ctx);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.tmp_dialog);


        mDialog.show();
    }


}
