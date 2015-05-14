package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Contiene {

    public static final String TABLE_CONTIENE = "Contiene";
    // Contiene Columns
    public static final String CONTIENE_PK_ORDINE_DATA = "ordine_data";
    public static final String CONTIENE_PK_ORDINE_FORNITORE = "ordine_fornitore";
    public static final String CONTIENE_PK_PEZZO = "pezzo";
    public static final String CONTIENE_NUMERO_PEZZI = "numero_pezzi";
    public static final String CONTIENE_PREZZO_PEZZO = "prezzo_pezzo";

    public static final String CONTIENE_COLUMS = "(" + CONTIENE_PK_ORDINE_DATA + ", "
            + CONTIENE_PK_ORDINE_FORNITORE + ", "
            + CONTIENE_PK_PEZZO + ", "
            + CONTIENE_NUMERO_PEZZI + ", "
            + CONTIENE_PREZZO_PEZZO + ")";

    private String ordine_data; //PRIMARY-KEY //FOREING KEY
    private String ordine_fornitore; //PRIMARY-KEY //FOREING KEY
    private int pezzo; //PRIMARY-KEY //FOREING KEY
    private int numero_pezzi;
    private float prezzo_pezzo;

    public Contiene(String ordine_data, String ordine_fornitore, int pezzo, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_CONTIENE;
            params[1] = "(" + CONTIENE_PK_ORDINE_DATA + ", " + CONTIENE_PK_ORDINE_FORNITORE + ", " + CONTIENE_PK_PEZZO + ")";
            params[2] = "('" + ordine_data + "', '" + ordine_fornitore + "', " + pezzo + ")";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }
        this.ordine_data = ordine_data;
        this.ordine_fornitore = ordine_fornitore;
        this.pezzo = pezzo;
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_CONTIENE;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = "(" + CONTIENE_PK_ORDINE_DATA + ", " + CONTIENE_PK_ORDINE_FORNITORE + ", " + CONTIENE_PK_PEZZO + ")";
        params[4] = "('" + this.ordine_data + "', '" + this.ordine_data + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_CONTIENE;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + CONTIENE_PK_ORDINE_DATA + ", " + CONTIENE_PK_ORDINE_FORNITORE + ", " + CONTIENE_PK_PEZZO + ")";
        params[4] = "('" + this.ordine_data + "', '" + this.ordine_data + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(float nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_CONTIENE;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = "(" + CONTIENE_PK_ORDINE_DATA + ", " + CONTIENE_PK_ORDINE_FORNITORE + ", " + CONTIENE_PK_PEZZO + ")";
        params[4] = "('" + this.ordine_data + "', '" + this.ordine_data + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    /*public void updateDateInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_CONTIENE;
        params[1] = nome_attributo;
        params[2] = "(to_date('" + nuovo_valore + "', 'YYYY-MM-DD'))";
        params[3] = "(" + CONTIENE_PK_ORDINE_DATA + ", " + CONTIENE_PK_ORDINE_FORNITORE + ", " + CONTIENE_PK_PEZZO + ")";
        params[4] = "('" + this.ordine_data + "', '" + this.ordine_data + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }*/

    public String getOrdine_data() {
        return ordine_data;
    }

    public String getOrdine_fornitore() {
        return ordine_fornitore;
    }

    public int getPezzo() {
        return pezzo;
    }

    public int getNumero_pezzi() {
        return numero_pezzi;
    }

    public float getPrezzo_pezzo() {
        return prezzo_pezzo;
    }

    public void setPrezzo_pezzo(float prezzo_pezzo, boolean update) {
        this.prezzo_pezzo = prezzo_pezzo;
        if (update) {
            updateValueInDatabase(prezzo_pezzo, CONTIENE_PREZZO_PEZZO);
        }
    }

    public void setOrdine_data(String ordine_data, boolean update) {
        this.ordine_data = ordine_data;
        if (update) {
            updateValueInDatabase(ordine_data, CONTIENE_PK_ORDINE_DATA);
        }
    }

    public void setOrdine_fornitore(String ordine_fornitore, boolean update) {
        this.ordine_fornitore = ordine_fornitore;
        if (update) {
            updateValueInDatabase(ordine_fornitore, CONTIENE_PK_ORDINE_FORNITORE);
        }
    }

    public void setPezzo(int pezzo, boolean update) {
        this.pezzo = pezzo;
        if (update) {
            updateValueInDatabase(pezzo, CONTIENE_PK_PEZZO);
        }
    }

    public void setNumero_pezzi(int numero_pezzi, boolean update) {
        this.numero_pezzi = numero_pezzi;
        if (update) {
            updateValueInDatabase(numero_pezzi, CONTIENE_NUMERO_PEZZI);
        }
    }

}
