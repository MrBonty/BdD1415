package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class R8 {

    public static final String TABLE_R8 = "R8";
    // R8 Columns
    public static final String R8_PK_LAVORO = "lavoro";
    public static final String R8_PK_MANUTENZIONE = "manutenzione";

    public static final String R8_COLUMS = "(" + R8_PK_LAVORO + ", " + R8_PK_MANUTENZIONE + ")";

    private int lavoro; //PRIMARY-KEY  //FOREIGN-KEY
    private int manutenzione; //PRIMARY-KEY  /FOREIGN-KEY

    public R8(int lavoro, int manutenzione, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_R8;
            params[1] = "(" + R8_PK_LAVORO + ", " + R8_PK_MANUTENZIONE + ")";
            params[2] = "(" + lavoro + ", " + manutenzione + ")";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }
        this.lavoro = lavoro;
        this.manutenzione = manutenzione;
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_R8;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + R8_PK_LAVORO + ", " + R8_PK_MANUTENZIONE + ")";
        params[4] = "(" + this.lavoro + ", " + this.manutenzione + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public int getLavoro() {
        return lavoro;
    }

    public int getManutenzione() {
        return manutenzione;
    }

    public void setLavoro(int lavoro, boolean update) {
        this.lavoro = lavoro;
        if (update) {
            updateValueInDatabase(lavoro, R8_PK_LAVORO);
        }
    }

    public void setManutenzione(int manutenzione, boolean update) {
        this.manutenzione = manutenzione;
        if (update) {
            updateValueInDatabase(manutenzione, R8_PK_MANUTENZIONE);
        }
    }
}
