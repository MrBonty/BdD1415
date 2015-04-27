package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Usato {

    public static final String TABLE_USATO = "Usato";
    // Usato Columns
    public static final String USATO_PK_LAVORO = "lavoro";
    public static final String USATO_PK_PEZZO = "pezzo";
    public static final String USATO_NUMERO_PEZZI = "numero_pezzi";

    public static final String USATO_COLUMS = "(" + USATO_PK_LAVORO + ", "
            + USATO_PK_PEZZO + ", "
            + USATO_NUMERO_PEZZI + ")";

    private int lavoro; //PRIMARY-KEY
    private int pezzo; //PRIMARY-KEY
    private int numero_pezzi;

    public Usato(int lavoro, int pezzo, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_USATO;
            params[1] = "(" + USATO_PK_LAVORO + ", " + USATO_PK_PEZZO + ")";
            params[2] = "(" + lavoro + ", '" + pezzo + "')";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.lavoro = lavoro;
        this.pezzo = pezzo;

        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_USATO;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + USATO_PK_LAVORO + ", " + USATO_PK_PEZZO + ")";
        params[4] = "(" + this.lavoro + ", " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public int getLavoro() {
        return lavoro;
    }

    public int getPezzo() {
        return pezzo;
    }

    public int getNumero_pezzi() {
        return numero_pezzi;
    }

    public void setNumero_pezzi(int numero_pezzi, boolean upgrade) {
        this.numero_pezzi = numero_pezzi;
        if (upgrade) {
            updateValueInDatabase(numero_pezzi, USATO_NUMERO_PEZZI);
        }
    }

    public void setPezzo(int pezzo, boolean update) {
        this.pezzo = pezzo;
        if (update) {
            updateValueInDatabase(pezzo, USATO_PK_PEZZO);
        }
    }

    public void setLavoro(int lavoro, boolean update) {
        this.lavoro = lavoro;
        if (update) {
            updateValueInDatabase(lavoro, USATO_PK_LAVORO);
        }
    }


}
