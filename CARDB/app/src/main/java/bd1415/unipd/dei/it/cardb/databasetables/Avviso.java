package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;
import bd1415.unipd.dei.it.cardb.insertclasses.InsertAvvisoInDataBase;

public class Avviso {


    public static final String TABLE_AVVISO = "Avviso";
    // Avviso Columns
    public static final String AVVISO_PK_MANUTENZIONE = "manutenzione";
    public static final String AVVISO_PK_VEICOLO = "veicolo";
    public static final String AVVISO_DATA_POSSIMA = "data_possima";

    private int manutenzione; //PRIMARY-KEY //FOREYNG KEY
    private String veicolo; //PRIMARY-KEY //FOREYNG KEY
    private String data_possima;
    private String resulte;

    public Avviso(int manutenzione, String veicolo){
        String[] params = new String[3];
        params[0] = TABLE_AVVISO;
        params[1] = "(" + AVVISO_PK_MANUTENZIONE + "," + AVVISO_PK_VEICOLO + ")";
        params[2] = "(" +  manutenzione + ", '"+veicolo+"')";
        params[3] = ";"; //TODO ALL ALL ALL ALL
        //TODO new InsertAvvisoInDataBase.execute(params);

        while (!Util.isSet());
        process(Util.getOutput());
        Util.setToNull();
    }

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_AVVISO;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(manutenzione, veicolo)";
        params[4] = "(" + this.manutenzione + ", '" + this.veicolo + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_AVVISO;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(manutenzione, veicolo)";
        params[4] = "(" + this.manutenzione + ", '" + this.veicolo + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public int getManutenzione() {
        return manutenzione;
    }

    public String getVeicolo() {
        return veicolo;
    }

    public String getData_possima() {
        return data_possima;
    }

    public void setManutenzione(int manutenzione) {
        this.manutenzione = manutenzione;
    }

    public void setData_possima(String data_possima) {
        this.data_possima = data_possima;
    }

    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
    }

    public static void process(String result){

    }
}
