package bd1415.unipd.dei.it.cardb.databasetables;

import java.util.concurrent.ExecutionException;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Pezzo {

    public static final String TABLE_PEZZO = "Pezzo";
    // Pezzo Columns
    public static final String PEZZO_PK_ID = "id";
    public static final String PEZZO_DESCRIZIONE = "descrizione";
    public static final String PEZZO_NUMERO_TOTALE_PEZZI = "numero_totale_pezzi";
    public static final String PEZZO_PREZZO_VENDITA = "prezzo_vedita";

    public static final String PEZZO_COLUMS = "(" + PEZZO_PK_ID + ", "
            + PEZZO_DESCRIZIONE + ")";

    private int id; //PRIMARY-KEY
    private String descrizione;
    private int numero_totale_pezzi;
    private float prezzo_vendita;

    public Pezzo(boolean insert) {
        if (insert) {
            String[] params = new String[5];
            params[0] = TABLE_PEZZO;
            params[1] = " DEFAULT ";
            params[2] = "";
            params[3] = " RETURNING " + PEZZO_PK_ID + ";";
            params[4] = PEZZO_PK_ID;
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
        params[0] = TABLE_PEZZO;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = PEZZO_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PEZZO;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = PEZZO_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(float nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PEZZO;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = PEZZO_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getNumero_totale_pezzi() {
        return numero_totale_pezzi;
    }

    public float getPrezzo_vendita() {
        return prezzo_vendita;
    }

    public void setId(int id, boolean update) {
        this.id = id;
        if (update) {
            updateValueInDatabase(id, PEZZO_PK_ID);
        }
    }

    public void setDescrizione(String descrizione, boolean update) {
        this.descrizione = descrizione;
        if (update) {
            updateValueInDatabase(descrizione, PEZZO_DESCRIZIONE);
        }
    }

    public void setNumero_totale_pezzi(int numero_totale_pezzi, boolean update) {
        this.numero_totale_pezzi = numero_totale_pezzi;
        if (update) {
            updateValueInDatabase(numero_totale_pezzi, PEZZO_NUMERO_TOTALE_PEZZI);
        }
    }

    public void setPrezzo_vendita(float prezzo_vendita, boolean update) {
        this.prezzo_vendita = prezzo_vendita;
        if (update) {
            updateValueInDatabase(prezzo_vendita, PEZZO_PREZZO_VENDITA);
        }
    }

}

