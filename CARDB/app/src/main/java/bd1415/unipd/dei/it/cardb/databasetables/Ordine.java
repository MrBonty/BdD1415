package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Ordine {

    public static final String TABLE_ORDINE = "Ordine";
    // Ordine Columns
    public static final String ORDINE_PK_DATA_OR = "dat_or";
    public static final String ORDINE_PK_FORNITORE = "fornitore";
    public static final String ORDINE_QUANTITA_FORNITURA = "quantita_fornitura";

    public static final String ORDINE_COLUMS = "(" + ORDINE_PK_DATA_OR + ", "
            + ORDINE_PK_FORNITORE + ", "
            + ORDINE_QUANTITA_FORNITURA + ")";

    private String data_or; //PRIMARY-KEY
    private String fornitore; //PRIMARY-KEY
    private int quantita_fornita;

    public Ordine(String data_or, String fornitore, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_ORDINE;
            params[1] = "(" + ORDINE_PK_DATA_OR + ", " + ORDINE_PK_FORNITORE + ")";
            params[2] = "(" + data_or + ", '" + fornitore + "')";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.data_or = data_or;
        this.fornitore = fornitore;

        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

        public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_ORDINE;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(" + ORDINE_PK_DATA_OR + ", " + ORDINE_PK_FORNITORE + ")";
        params[4] = "('" + this.data_or + "', '" + this.fornitore + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_ORDINE;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(" + ORDINE_PK_DATA_OR + ", " + ORDINE_PK_FORNITORE + ")";
        params[4] = "('" + this.data_or + "', '" + this.fornitore + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public String getData_or() {
        return data_or;
    }

    public String getFornitore() {
        return fornitore;
    }

    public int getQuantita_fornita() {
        return quantita_fornita;
    }

    public void setData_or(String data_or, boolean update) {
        this.data_or = data_or;
        if (update) {
            updateValueInDatabase(data_or, ORDINE_PK_DATA_OR);
        }
    }

    public void setFornitore(String fornitore, boolean update) {
        this.fornitore = fornitore;
        if (update) {
            updateValueInDatabase(fornitore, ORDINE_PK_FORNITORE);
        }
    }

    public void setQuantita_fornita(int quantita_fornita, boolean update) {
        this.quantita_fornita = quantita_fornita;
        if (update) {
            updateValueInDatabase(quantita_fornita, ORDINE_QUANTITA_FORNITURA);
        }
    }

}
