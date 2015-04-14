package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Ordine {

    private String data_or; //PRIMARY-KEY
    private String fornitore; //PRIMARY-KEY
    private int quantita_fornita;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Ordine";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(data_or, fornitore)";
        params[4] = "('" + this.data_or + "', '" + this.fornitore + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Ordine";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(data_or, fornitore)";
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

    public void setData_or(String data_or) {
        this.data_or = data_or;
    }

    public void setFornitore(String fornitore) {
        this.fornitore = fornitore;
    }

    public void setQuantita_fornita(int quantita_fornita) {
        this.quantita_fornita = quantita_fornita;
    }

}
