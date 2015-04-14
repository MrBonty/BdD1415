package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Avviso {

    private int manutenzione; //PRIMARY-KEY
    private String veicolo; //PRIMARY-KEY
    private String data_possima;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Contiene";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(manutenzione, veicolo)";
        params[4] = "(" + this.manutenzione + ", '" + this.veicolo + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Edficio";
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

}
