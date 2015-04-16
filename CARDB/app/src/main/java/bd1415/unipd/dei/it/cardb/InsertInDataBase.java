package bd1415.unipd.dei.it.cardb;

import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

import bd1415.unipd.dei.it.cardb.ConnectionWithDataBase;
import bd1415.unipd.dei.it.cardb.LoginDialog;
import bd1415.unipd.dei.it.cardb.MainActivity;
import bd1415.unipd.dei.it.cardb.databasetables.Avviso;

public class InsertInDataBase extends AsyncTask<String, Void, String> {

    //TODO create a generic one...

    private Toast toast;

    private int manutenzione = -1; //PRIMARY-KEY //>0
    private String veicolo = null; //PRIMARY-KEY
    private String data_possima = null;

    @Override
    protected void onPreExecute() {
        // here go all the graphics things
        toast = Toast.makeText(MainActivity.ctx, "Connecting...", Toast.LENGTH_SHORT);
        toast.show();

    }

    protected String doInBackground(String... params) {
        String s = "";
        Statement st = null;
        try {
            st = ConnectionWithDataBase.con.createStatement();
            if (st != null && ConnectionWithDataBase.con != null) {
                ResultSet rs = st.executeQuery("INSERT INTO main." + params[0] + params[1] + " VALUES "
                        + params[2] + params[3]);
                while (rs.next()) {
                    s = s + rs.getString(params[0]);

                }
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            }
            MainActivity.errorRetrievingData = false;
        } catch (Exception e) {
            MainActivity.errorRetrievingData = true;
            s = "Errore di reperimento dei dati, contattare l'amministratore del servizio.";
            return s;
        }
        return s;
    }

    @Override
    protected void onPostExecute(String result) {
        // here go all the graphics things
        if (MainActivity.isWrong) {
            toast.cancel();
            Toast.makeText(
                    MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
            new LoginDialog(MainActivity.ctx, MainActivity.act);
        } else if (MainActivity.errorRetrievingData) {
            toast.cancel();
            Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
        } else {
            toast.cancel();
            Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_LONG).show();
        }

        Util.setOutput(result);
    }

}
