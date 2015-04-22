package bd1415.unipd.dei.it.cardb;

import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class InsertInDataBase extends AsyncTask<String, Void, String> {

    private static final int MIN_PARAM_LENGTH = 4; //minimal legth of params array: params[0]...
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
                ResultSet rs = st.executeQuery("INSERT INTO main." + params[0] + params[1] + " VALUES "
                        + params[2] + params[3]);
                int parLen = params.length;
                while (rs.next() && parLen> MIN_PARAM_LENGTH && params[MIN_PARAM_LENGTH] != null) {
                    for(int i = MIN_PARAM_LENGTH; i< parLen; i++) {
                        s = s + rs.getString(params[i])+ Util.DIVISOR;
                    }
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
