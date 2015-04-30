package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class R7 {

    public static final String TABLE_R7 = "r7";
    // R7 Columns
    public static final String R7_PK_LAVORO = "lavoro";
    public static final String R7_PK_GUASTO = "guasto";

    public static final String R7_COLUMS = "(" + R7_PK_LAVORO + ", " + R7_PK_GUASTO + ")";

    private int lavoro; //PRIMARY-KEY //FOREIGN-KEY
    private int guasto; //PRIMARY-KEY //FOREIGN-KEY

    public R7(int lavoro, int guasto, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_R7;
            params[1] = "(" + R7_PK_LAVORO + ", " + R7_PK_GUASTO + ")";
            params[2] = "(" + lavoro + ", " + guasto + ")";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.lavoro = lavoro;
        this.guasto = guasto;

        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_R7;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + R7_PK_LAVORO + ", " + R7_PK_GUASTO + ")";
        params[4] = "(" + this.lavoro + ", " + this.guasto + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public void setGuasto(int guasto, boolean update) {
        this.guasto = guasto;
        if (update) {
            updateValueInDatabase(guasto, R7_PK_GUASTO);
        }
    }

    public void setLavoro(int lavoro, boolean update) {
        this.lavoro = lavoro;
        if (update) {
            updateValueInDatabase(lavoro, R7_PK_LAVORO);
        }
    }

    public int getGuasto() {
        return guasto;
    }

    public int getLavoro() {
        return lavoro;
    }
}
