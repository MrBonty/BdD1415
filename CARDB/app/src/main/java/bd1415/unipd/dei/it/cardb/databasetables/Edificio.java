package bd1415.unipd.dei.it.cardb.databasetables;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Edificio {

    public static final String TABLE_EDIFICIO = "Edificio";
    // Edificio Columns
    public static final String EDIFICIO_PK_ID = "id";
    public static final String EDIFICIO_TIPOLOGIA = "tipologia";
    public static final String EDIFICIO_INDIRIZZO = "indirizzo";

    public static final String EDIFICIO_COLUMS = "(" + EDIFICIO_PK_ID + ", "
            + EDIFICIO_TIPOLOGIA + ", "
            + EDIFICIO_INDIRIZZO + ")";

    private int id; //PRIMARY-KEY
    private String tipologia; //NOT NULL
    private AddressType indirizzo;

    public Edificio(String tipologia, boolean insert) {
        if (insert) {
            String[] params = new String[5];
            params[0] = TABLE_EDIFICIO;
            params[1] = "(" + EDIFICIO_TIPOLOGIA + ")";
            params[2] = "('" + tipologia + "')";
            params[3] = " RETURNING " + EDIFICIO_PK_ID + ";";
            params[4] = EDIFICIO_PK_ID;
            new InsertInDataBase().execute(params);
        }
        this.tipologia = tipologia;
    }


    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_EDIFICIO;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = EDIFICIO_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_EDIFICIO;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = EDIFICIO_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_EDIFICIO;
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = EDIFICIO_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public String getTipologia() {
        return tipologia;
    }

    public AddressType getIndirizzo() {
        return indirizzo;
    }

    public void setId(int id, boolean upgrade) {
        this.id = id;
        if (upgrade) {
            updateValueInDatabase(id, EDIFICIO_PK_ID);
        }
    }

    public void setTipologia(String tipologia, boolean update) {
        this.tipologia = tipologia;
        if (update) {
            updateValueInDatabase(tipologia, EDIFICIO_TIPOLOGIA);
        }
    }

    public void setInditizzo(AddressType indirizzo, boolean update) {
        this.indirizzo = indirizzo;
        if (update) {
            updateValueInDatabase(indirizzo, EDIFICIO_INDIRIZZO);
        }
    }

}
