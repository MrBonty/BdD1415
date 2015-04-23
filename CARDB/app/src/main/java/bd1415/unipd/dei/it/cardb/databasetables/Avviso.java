package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Avviso {

    public static final String TABLE_AVVISO = "Avviso";
    // Avviso Columns
    public static final String AVVISO_PK_MANUTENZIONE = "manutenzione";
    public static final String AVVISO_PK_VEICOLO = "veicolo";
    public static final String AVVISO_DATA_PROSSIMA = "data_prossima";

    public static final String AVVISO_COLUMS = "(" + AVVISO_PK_MANUTENZIONE + ", "
                                               + AVVISO_PK_VEICOLO + ", "
                                               + AVVISO_DATA_PROSSIMA + ")";



    private int manutenzione; //PRIMARY-KEY //FOREING KEY
    private String veicolo; //PRIMARY-KEY //FOREING KEY
    private String data_possima;

    public Avviso(int manutenzione, String veicolo, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_AVVISO;
            params[1] = "(" + AVVISO_PK_MANUTENZIONE + ", " + AVVISO_PK_VEICOLO + ")";
            params[2] = "(" + manutenzione + ", '" + veicolo + "')";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }
        this.manutenzione = manutenzione;
        this.veicolo = veicolo;
        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_AVVISO;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = "(" + AVVISO_PK_MANUTENZIONE + ", " + AVVISO_PK_VEICOLO + ")";
        params[4] = "(" + this.manutenzione + ", '" + this.veicolo + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_AVVISO;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + AVVISO_PK_MANUTENZIONE + ", " + AVVISO_PK_VEICOLO + ")";
        params[4] = "(" + this.manutenzione + ", '" + this.veicolo + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public int getManutenzione() {
        return manutenzione;
    }

    public void setManutenzione(int manutenzione, boolean update) {
        this.manutenzione = manutenzione;
        if(update) {
            updateValueInDatabase(manutenzione, AVVISO_PK_MANUTENZIONE);
        }
    }

    public String getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(String veicolo, boolean update) {
        this.veicolo = veicolo;
        if(update) {
            updateValueInDatabase(veicolo, AVVISO_PK_VEICOLO);
        }
    }

    public String getData_possima() {
        return data_possima;
    }

    public void setData_possima(String data_possima, boolean update) {
        this.data_possima = data_possima;
        if(update) {
            updateValueInDatabase(data_possima, AVVISO_DATA_PROSSIMA);
        }
    }

}
