package bd1415.unipd.dei.it.cardb.databasetables;

import java.util.concurrent.ExecutionException;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Manutenzione {

    public static final String TABLE_MANUTENZIONE = "Manutenzione";
    // Manutenzione Columns
    public static final String MANUTENZIONE_PK_ID = "id";
    public static final String MANUTENZIONE_DESCRIZIONE = "descrizione";

    public static final String MANUTENZIONE_COLUMS = "(" + MANUTENZIONE_PK_ID + ", "
            + MANUTENZIONE_DESCRIZIONE + ")";

    private int id; //PRIMARY-KEY
    private String descrizione;

    public Manutenzione(boolean insert) {
        if (insert) {
            String[] params = new String[5];
            params[0] = TABLE_MANUTENZIONE;
            params[1] = " DEFAULT ";
            params[2] = "";
            params[3] = " RETURNING " + MANUTENZIONE_PK_ID + ";";
            params[4] = MANUTENZIONE_PK_ID;
            try {
                this.id = Integer.parseInt(Util.process(new InsertInDataBase().execute(params).get())[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_MANUTENZIONE;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = MANUTENZIONE_PK_ID;
        params[4] = "'" + this.id + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_MANUTENZIONE;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = MANUTENZIONE_PK_ID;
        params[4] = "'" + this.id + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setId(int id, boolean update) {
        this.id = id;
        if (update) {
            updateValueInDatabase(id, MANUTENZIONE_PK_ID);
        }
    }

    public void setDescrizione(String descrizione, boolean update) {
        this.descrizione = descrizione;
        if (update) {
            updateValueInDatabase(descrizione, MANUTENZIONE_DESCRIZIONE);
        }
    }
}
