package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Lavoro {

    public static final String TABLE_LAVORO = "Lavoro";
    // Lavoro Columns
    public static final String LAVORO_PK_ID = "id";
    public static final String LAVORO_DATA_INIZIO = "data_inzio";
    public static final String LAVORO_DATA_FINE = "data_fine";
    public static final String LAVORO_VEICOLO = "veicolo";
    public static final String LAVORO_FATTURA = "fattura";

    private int id; //PRIMARY-KEY
    private String data_inizio;
    private String data_fine;
    private String veicolo; //FOREING KEY
    private int fattura;

    public Lavoro(String veicolo){
        String[] params = new String[5];
        params[0] = TABLE_LAVORO;
        params[1] = "(" + LAVORO_VEICOLO + ")";
        params[2] = "('" + veicolo + "')";
        params[3] = " RETURNING " + LAVORO_PK_ID + ";";
        params[4] = LAVORO_PK_ID;
        new InsertInDataBase().execute(params);

        this.veicolo = veicolo;

        while (!Util.isSet());
        String[] tmp = Util.getOutput();
        id = Integer.parseInt(tmp[0]);
        Util.setToNull();
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_LAVORO;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = LAVORO_PK_ID;
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_LAVORO;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = LAVORO_PK_ID;
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public String getData_inizio() {
        return data_inizio;
    }

    public String getData_fine() {
        return data_fine;
    }

    public String getVeicolo() {
        return veicolo;
    }

    public int getFattura() {
        return fattura;
    }

    public void setId(int id) {
        this.id = id;
        updateValueInDatabase(id, LAVORO_PK_ID);
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
        updateValueInDatabase(data_inizio, LAVORO_DATA_INIZIO);
    }

    public void setData_fine(String data_fine) {
        this.data_fine = data_fine;
        updateValueInDatabase(data_fine, LAVORO_DATA_FINE);
    }

    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
        updateValueInDatabase(veicolo, LAVORO_VEICOLO);
    }

    public void setFattura(int fattura) {
        this.fattura = fattura;
        updateValueInDatabase(fattura, LAVORO_FATTURA);
    }

}
