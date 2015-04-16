package bd1415.unipd.dei.it.cardb.insertclasses;

import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

import bd1415.unipd.dei.it.cardb.ConnectionWithDataBase;
import bd1415.unipd.dei.it.cardb.LoginDialog;
import bd1415.unipd.dei.it.cardb.MainActivity;
import bd1415.unipd.dei.it.cardb.databasetables.AddressType;

public class InsertPersonaleInDataBase extends AsyncTask<String, Void, String> {

    private Toast toast;

    public static final String TABLE_PERSONALE = "Personale";
    // Personale Columns
    public static final String PERSONALE_PK_CF = "cf";
    public static final String PERSONALE_NOME = "nome";
    public static final String PERSONALE_COGNOME = "cognome";
    public static final String PERSONALE_TELEFONO = "telefono";
    public static final String PERSONALE_INDIRIZZO = "indirizzo";
    public static final String PERSONALE_CIVICO = "civico";
    public static final String PERSONALE_CITTA = "città";
    public static final String PERSONALE_PROVINCIA = "provincia";
    public static final String PERSONALE_IBAN = "iban";
    public static final String PERSONALE_CONTRATTO = "contratto";
    public static final String PERSONALE_RESPONSABILE = "responsabile";
    public static final String PERSONALE_EDIFICIO = "edificio";

    private String cf = null; //PRIMARY-KEY
    private String nome = null;
    private String cognome = null;
    private String telefono = null;
    private AddressType indirizzo = null;
    private String iban = null;
    private String contratto = null;
    private int responsabile = 0; // boolean
    private int edificio = -1; //>0

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
    }

}
