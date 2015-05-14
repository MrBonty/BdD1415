package bd1415.unipd.dei.it.cardb;

import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateValueInDataBase extends AsyncTask<String, Void, String> {

    private Toast toast;

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
                ResultSet rs = st.executeQuery("UPDATE main." + params[0] + " SET " + params[1] + " = "
                        + params[2] + " WHERE " + params[3] + " = " + params[4] + " RETURNING " + params[0] + ";");
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
            s = "Errore di inserimento dei dati, contattare l'amministratore del servizio.";
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
            new LoginDialog(MainActivity.ctx);
        } else if (MainActivity.errorRetrievingData) {
            toast.cancel();
            Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
        } else {
            toast.cancel();
            Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}
