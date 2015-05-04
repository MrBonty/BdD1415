package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;

import bd1415.unipd.dei.it.cardb.databasetables.Fattura;

public class FatturaDialog  {

    final Dialog mDialog;
    //TODO
    public FatturaDialog(Fattura fattura) {
        mDialog = new Dialog(MainActivity.ctx);
        mDialog.show();
    }


}
