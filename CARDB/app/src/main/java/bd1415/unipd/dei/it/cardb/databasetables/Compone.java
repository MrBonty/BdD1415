package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Compone {

    public static final String TABLE_COMPONE = "Compone";
    // Compone Columns
    public static final String COMPONE_PK_MODELLO_COD_PROD = "modello_cod_prod";
    public static final String COMPONE_PK_MODELLO_MARCA = "modello_marca";
    public static final String COMPONE_PK_PEZZO = "pezzo";

    public static final String COMPONE_COLUMS = "(" + COMPONE_PK_MODELLO_COD_PROD + ", "
            + COMPONE_PK_MODELLO_MARCA + ", "
            + COMPONE_PK_PEZZO + ")";

    private String modello_cod_prod; //PRIMARY-KEY //FOREIGN KEY
    private String modello_marca; //PRIMARY-KEY //FOREIGN KEY
    private int pezzo; //PRIMARY-KEY //FOREIGN KEY

    public Compone(String modello_cod_prod, String modello_marca, int pezzo, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_COMPONE;
            params[1] = "(" + COMPONE_PK_MODELLO_COD_PROD + ", " + COMPONE_PK_MODELLO_MARCA + ", " + COMPONE_PK_PEZZO + ")";
            params[2] = "('" + modello_cod_prod + "', '" + modello_marca + "', " + pezzo + ")";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.modello_cod_prod = modello_cod_prod;
        this.modello_marca = modello_marca;
        this.pezzo = pezzo;

        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_COMPONE;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = "(" + COMPONE_PK_MODELLO_COD_PROD + ", " + COMPONE_PK_MODELLO_MARCA + ", " + COMPONE_PK_PEZZO + ")";
        params[4] = "('" + this.modello_cod_prod + "', '" + this.modello_marca + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_COMPONE;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + COMPONE_PK_MODELLO_COD_PROD + ", " + COMPONE_PK_MODELLO_MARCA + ", " + COMPONE_PK_PEZZO + ")";
        params[4] = "('" + this.modello_cod_prod + "', '" + this.modello_marca + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public String getModello_cod_prod() {
        return modello_cod_prod;
    }

    public String getModello_marca() {
        return modello_marca;
    }

    public int getPezzo() {
        return pezzo;
    }

    public void setModello_cod_prod(String modello_cod_prod, boolean update) {
        this.modello_cod_prod = modello_cod_prod;
        if (update) {
            updateValueInDatabase(modello_cod_prod, COMPONE_PK_MODELLO_COD_PROD);
        }
    }

    public void setModello_marca(String modello_marca, boolean update) {
        this.modello_marca = modello_marca;
        if (update) {
            updateValueInDatabase(modello_marca, COMPONE_PK_MODELLO_MARCA);
        }
    }

    public void setPezzo(int pezzo, boolean update) {
        this.pezzo = pezzo;
        if (update) {
            updateValueInDatabase(pezzo, COMPONE_PK_PEZZO);
        }
    }

}
