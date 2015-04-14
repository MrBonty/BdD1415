package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Lavoro {

    private int id; //PRIMARY -KEY
    private String data_inizio;
    private String data_fine;
    private String veicolo;
    private int fattura;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Lavoro";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Lavoro";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "id";
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
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
    }

    public void setData_fine(String data_fine) {
        this.data_fine = data_fine;
    }

    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
    }

    public void setFattura(int fattura) {
        this.fattura = fattura;
    }

}
