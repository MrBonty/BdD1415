package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Fattura {

    public static final String TABLE_FATTURA = "Fattura";
    // Fattura Columns
    public static final String FATTURA_PK_ID = "id";
    public static final String FATTURA_PAGATO = "pagato";
    public static final String FATTURA_AZIENDA = "azienda";
    public static final String FATTURA_PRIVATO = "privato";

    public static final String FATTURA_COLUMS = "(" + FATTURA_PK_ID + ", "
            + FATTURA_PAGATO + ", "
            + FATTURA_AZIENDA + ", "
            + FATTURA_PRIVATO + ")";

    private int id; //PRIMARY-KEY
    private int pagato = 0;
    //TODO -- CHECK IT
    private String azienda; //FORING KEY but not null
    private String privato; //FORING KEY but not null

    public Fattura(boolean insert) {
        if (insert) {
            String[] params = new String[5];
            params[0] = TABLE_FATTURA;
            params[1] = " DEFAULT ";
            params[2] = "";
            params[3] = " RETURNING " + FATTURA_PK_ID + ";";
            params[4] = FATTURA_PK_ID;
            new InsertInDataBase().execute(params);


            while (!Util.isSet()) ;
            String[] tmp = Util.getOutput();
            id = Integer.parseInt(tmp[0]);
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_FATTURA;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = FATTURA_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_FATTURA;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = FATTURA_PK_ID;
        params[4] = "" + this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public int getPagato() {
        return pagato;
    }

    public String getAzienda() {
        return azienda;
    }

    public String getPrivato() {
        return privato;
    }

    public void setId(int id, boolean update) {
        this.id = id;
        if (update) {
            updateValueInDatabase(id, FATTURA_PK_ID);
        }
    }

    public void setPagato(int pagato, boolean update) {
        this.pagato = pagato;
        if (update) {
            updateValueInDatabase(pagato, FATTURA_PAGATO);
        }
    }

    public void setAzienda(String azienda, boolean update) {
        this.azienda = azienda;
        if (update) {
            updateValueInDatabase(azienda, FATTURA_AZIENDA);
        }
    }

    public void setPrivato(String privato, boolean update) {
        this.privato = privato;
        if (update) {
            updateValueInDatabase(privato, FATTURA_PRIVATO);
        }
    }
}
